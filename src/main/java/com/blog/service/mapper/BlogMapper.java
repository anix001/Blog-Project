package com.blog.service.mapper;

import com.blog.domain.Blog;
import com.blog.domain.dto.BlogDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    BlogDTO domainTODto(Blog blog);
    List<BlogDTO> domainTODtos(List<Blog> blogList);
}
