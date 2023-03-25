package com.blog.dto.blog;

import com.blog.domain.blog.Blog;

import java.util.function.Function;

public class BlogDTOMapper implements Function<Blog, BlogDTO> {
    @Override
    public BlogDTO apply(Blog blog) {
        return new BlogDTO(
                blog.getCreatedBy(),
                blog.getCreatedDate(),
                blog.getLastModifiedBy(),
                blog.getLastModifiedDate(),
                blog.getId(),
                blog.getTitle(),
                blog.getDescription(),
                blog.getImageUrl(),
                blog.getCommentList()
        );
    }
}
