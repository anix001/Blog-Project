package com.blog.domain.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="blog_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="comments")
    private String Comments;
}
