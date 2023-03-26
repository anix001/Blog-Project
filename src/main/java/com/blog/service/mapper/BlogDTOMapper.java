package com.blog.service.mapper;

import com.blog.domain.Blog;
import com.blog.domain.dto.BlogDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BlogDTOMapper implements Function<Blog, BlogDTO> {
    @Override
    public BlogDTO apply(Blog blog) {
        return new BlogDTO(
                blog.getId(),
                blog.getTitle(),
                blog.getDescription(),
                blog.getImageUrl(),
                blog.getCommentList(),
                blog.getCreatedBy(),
                blog.getCreatedDate(),
                blog.getLastModifiedBy(),
                blog.getLastModifiedDate()
        );
    }
}
