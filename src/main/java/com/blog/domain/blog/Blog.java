package com.blog.domain.blog;

import com.blog.domain.auditable.Auditable;
import com.blog.domain.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blog_tbl")
public class Blog extends Auditable {
   @Id
   @GeneratedValue
   @Column(name="blog_id")
   private Long id;

   @Column(name="title")
   private String title;

   @Column(name="description")
   private String description;

   @Column(name="image_url")
   private String imageUrl;

   @OneToMany(cascade =CascadeType.ALL)
   @JoinColumn(name = "fk_blog_id", referencedColumnName = "blog_id")
   private List<Comment> commentList = new ArrayList<>();

   @ManyToOne
   private AppUser appUser;
}
