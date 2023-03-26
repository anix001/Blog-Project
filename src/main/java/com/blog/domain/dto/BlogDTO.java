package com.blog.domain.dto;

import com.blog.domain.Comment;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogDTO extends AuditableDTO {
    private Long id;
    private String title;
    private String description;
    private Long blogCode;
    private String imageUrl;
    private List<Comment> commentList;

    public BlogDTO(Long id, String title, String description, String imageUrl, List<Comment> commentList, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate) {
        super();
    }
}
