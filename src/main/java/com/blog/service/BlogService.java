package com.blog.service;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.dto.BlogDTO;

import java.util.List;

public interface BlogService {
  List<BlogDTO> index();
  BlogDTO indexById(Long blogId);
  List<BlogDTO> indexByUser(Long userId);
  BlogDTO store(Long blogId, Blog blog);
  void destroy(Long blogId);
  BlogDTO comment(Long blogId, Comment comment);
}
