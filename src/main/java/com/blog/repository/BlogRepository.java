package com.blog.repository;

import com.blog.domain.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(
            "select blogs from Blog as blogs where blogs.appUser.id =: id"
    )
    List<Blog> getBlogsByUserId(@Param("id") Long id);
}
