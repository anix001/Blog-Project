package com.blog.domain.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="blog_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="comments")
    private String Comments;
}
