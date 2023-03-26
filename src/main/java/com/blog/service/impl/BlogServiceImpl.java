package com.blog.service.impl;

import com.blog.domain.blog.Blog;
import com.blog.domain.blog.Comment;
import com.blog.domain.user.AppUser;
import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.BlogDTOMapper;
import com.blog.repository.BlogRepository;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogDTOMapper blogDTOMapper;
    private final UserService userService;

    public BlogServiceImpl(BlogRepository blogRepository, BlogDTOMapper blogDTOMapper, UserService userService) {
        this.blogRepository = blogRepository;
        this.blogDTOMapper = blogDTOMapper;
        this.userService = userService;
    }

    @Override
    public List<BlogDTO> index() {
        List<Blog> blogList = blogRepository.findAll();
        List<BlogDTO> response = new ArrayList<>();

        blogList.forEach(blog->{
            BlogDTO blogDTO = blogDTOMapper.apply(blog);
            response.add(blogDTO);
        });
        return response;
    }

    @Override
    public BlogDTO indexById(Long blogId) {
        Blog blog= blogRepository.findById(blogId)
                .orElseThrow(()-> new IllegalStateException("Blog with id "+ blogId + " not Found"));
        BlogDTO blogDTO = blogDTOMapper.apply(blog);
        return blogDTO;
    }

    @Override
    public List<BlogDTO> indexByUser(Long userId) {
        List<Blog> blogList = blogRepository.getBlogsByUserId(userId);

        List<BlogDTO> response = new ArrayList<>();

        blogList.forEach(blog->{
            BlogDTO blogDTO = blogDTOMapper.apply(blog);
            response.add(blogDTO);
        });
        return response;
    }

    @Override
    public BlogDTO store(Long blogId, Blog blog) {
        AppUser user = userService.currentUser();
        if(blogId != null){
            Blog optionalBlog = blogRepository.findById(blogId)
                    .orElseThrow(()->new IllegalStateException("Blog with id "+ blogId + " not Found"));

            if(blog.getAppUser().getEmail() == user.getEmail()) {
                optionalBlog.setTitle(blog.getTitle());
                optionalBlog.setDescription(blog.getDescription());
                optionalBlog.setImageUrl(blog.getImageUrl());
                optionalBlog.setCommentList(blog.getCommentList());
                optionalBlog.setLastModifiedBy(user.getEmail());
                optionalBlog.setLastModifiedDate(new Date());
            }
            blogRepository.save(optionalBlog);
            BlogDTO blogDTO = blogDTOMapper.apply(optionalBlog);
            return  blogDTO;
        }else {
            blogRepository.save(blog);
            BlogDTO blogDTO = blogDTOMapper.apply(blog);
            return  blogDTO;
        }
    }

    @Override
    public void destroy(Long blogId) {
        Blog blog= blogRepository.findById(blogId)
                .orElseThrow(()-> new IllegalStateException("Blog with id "+ blogId + " not Found"));

        AppUser currentUser = userService.currentUser();

        if(currentUser.getEmail() == blog.getAppUser().getEmail()){
            blogRepository.deleteById(blogId);
        }else{
            throw(new IllegalStateException("You are not authorized to delete this blog"));
        }
    }

    @Override
    public BlogDTO comment(Long blogId, Comment comment) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(()-> new IllegalStateException("Blog with id "+ blogId + " not Found"));
        List<Comment> commentList = new ArrayList<>();
        if(comment != null){
            commentList.add(comment);
        }
        blog.setCommentList(commentList);

        blogRepository.save(blog);
        BlogDTO blogDTO = blogDTOMapper.apply(blog);
        return  blogDTO;
    }
}
