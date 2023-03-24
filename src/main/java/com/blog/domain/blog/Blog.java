package com.blog.domain.blog;

import com.blog.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class Blog {
   @Id
   @GeneratedValue
   @Column(name="blog_id")
   private Long id;

   @Column(name="title")
   private String title;

   @Column(name="description")
   private String description;

   @Column(name="blog_code")
   private Long blogCode;

   @Column(name="image_url")
   private String imageUrl;

   @OneToMany(cascade =CascadeType.ALL)
   @JoinColumn(name = "fk_blog_id", referencedColumnName = "blog_id")
   private List<Comment> commentList = new ArrayList<>();

   @ManyToOne
   private User user;
}
