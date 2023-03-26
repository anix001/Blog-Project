package com.blog.resources;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.domain.dto.BlogDTO;
import com.blog.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/blog")
public class BlogResources {
    private final BlogService blogService;

    public BlogResources(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping()
    public ResponseEntity<List<BlogDTO>> index(){
        return ResponseEntity.ok(blogService.index());
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDTO> indexById(@PathVariable("blogId") Long blogId){
        return ResponseEntity.ok(blogService.indexById(blogId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BlogDTO>> indexByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(blogService.indexByUser(userId));
    }

    @PostMapping("/store")
    public ResponseEntity<BlogDTO> store(@RequestParam(required = false) Long blogId,
                                         @RequestBody Blog blog){
        return ResponseEntity.ok(blogService.store(blogId, blog));
    }

    @DeleteMapping("/{blogId}")
    public void destroy(@PathVariable("blogId") Long blogId){
        blogService.destroy(blogId);
    }

    @PostMapping("/comment")
    public ResponseEntity<BlogDTO> comment(@RequestParam Long blogId,
                                           @RequestBody Comment comment){
        return ResponseEntity.ok(blogService.comment(blogId, comment));
    }
}
