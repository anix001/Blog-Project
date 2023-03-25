package com.blog.dto.blog;

import com.blog.domain.blog.Comment;
import com.blog.dto.auditable.AuditableDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogDTO extends AuditableDTO {
    private String title;
    private String description;
    private Long blogCode;
    private String imageUrl;
    private List<Comment> commentList;

    public BlogDTO(String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, String title, String description, Long blogCode, String imageUrl, List<Comment> commentList) {
        super();
    }
}