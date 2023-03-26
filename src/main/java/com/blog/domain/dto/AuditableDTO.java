package com.blog.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditableDTO {
    protected String createdBy;
    protected Date createdDate;
    protected String lastModifiedBy;
    protected Date lastModifiedDate;
}
