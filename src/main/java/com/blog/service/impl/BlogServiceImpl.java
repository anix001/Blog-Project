package com.blog.service.impl;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.AppUser;
import com.blog.domain.dto.BlogDTO;
import com.blog.domain.enumeration.UserStatus;
import com.blog.exceptionHandling.ApiRequestException;
import com.blog.repository.BlogRepository;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import com.blog.service.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;
    private final BlogMapper blogMapper;

    public BlogServiceImpl(BlogRepository blogRepository, UserService userService, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.userService = userService;
        this.blogMapper = blogMapper;
    }

    @Override
    public List<BlogDTO> index() {
        List<Blog> blogList = blogRepository.findAll();
        List<BlogDTO> response = blogMapper.domainTODtos(blogList);
        return response;
    }

    @Override
    public BlogDTO indexById(Long blogId) {
        Blog blog= blogRepository.findById(blogId)
                .orElseThrow(()-> new IllegalStateException("Blog with id "+ blogId + " not Found"));
        BlogDTO blogDTO = blogMapper.domainTODto(blog);
        return blogDTO;
    }

    @Override
    public List<BlogDTO> indexByUser(Long userId) {
        List<Blog> blogList = blogRepository.getBlogsByUserId(userId);

        List<BlogDTO> response = blogMapper.domainTODtos(blogList);

//        blogList.forEach(blog->{
//            BlogDTO blogDTO = blogDTOMapper.apply(blog);
//            response.add(blogDTO);
//        });
        return response;
    }

    @Override
    public BlogDTO store(Long blogId, Blog blog) {
        AppUser user = userService.currentUser();

        if(user.getUserStatus() == UserStatus.PENDING){
            throw new ApiRequestException("Account is not activated");
        }


        if(blogId != null){
            Blog optionalBlog = blogRepository.findById(blogId)
                    .orElseThrow(()->new ApiRequestException("Blog with id "+ blogId + " not Found"));

            if(optionalBlog.getAppUser().getEmail() == user.getEmail()) {
                optionalBlog.setTitle(blog.getTitle());
                optionalBlog.setDescription(blog.getDescription());
                optionalBlog.setImageUrl(blog.getImageUrl());
                optionalBlog.setCommentList(blog.getCommentList());
                optionalBlog.setLastModifiedBy(user.getEmail());
                optionalBlog.setLastModifiedDate(new Date());
            }

            blogRepository.save(optionalBlog);
            BlogDTO blogDTO = blogMapper.domainTODto(optionalBlog);
            return  blogDTO;
        }else {
            AppUser user1 = new AppUser();
            user1.setId(user.getId());
            blog.setCreatedBy(user.getEmail());
            blog.setCreatedDate(new Date());
            blog.setAppUser(user1);
            blogRepository.save(blog);
            BlogDTO blogDTO = blogMapper.domainTODto(blog);
            return  blogDTO;
        }
    }

    @Override
    public void destroy(Long blogId) {
        AppUser currentUser = userService.currentUser();
        if(currentUser.getUserStatus() == UserStatus.PENDING){
            throw new ApiRequestException("Account is not activated");
        }
        Blog blog= blogRepository.findById(blogId)
                .orElseThrow(()-> new IllegalStateException("Blog with id "+ blogId + " not Found"));



        if(currentUser.getEmail() == blog.getAppUser().getEmail()){
            blogRepository.deleteById(blogId);
        }else{
            throw(new IllegalStateException("You are not authorized to delete this blog"));
        }
    }

    @Override
    public BlogDTO comment(Long blogId, Comment comment) {
        AppUser currentUser = userService.currentUser();
        if(currentUser.getUserStatus() == UserStatus.PENDING){
            throw new ApiRequestException("Account is not activated");
        }
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(()-> new IllegalStateException("Blog with id "+ blogId + " not Found"));
        List<Comment> commentList = new ArrayList<>();
        if(comment != null){
            commentList.add(comment);
        }
        blog.setCommentList(commentList);

        blogRepository.save(blog);
        BlogDTO blogDTO = blogMapper.domainTODto(blog);
        return  blogDTO;
    }
}
