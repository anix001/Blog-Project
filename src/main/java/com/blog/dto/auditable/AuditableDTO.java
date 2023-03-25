package com.blog.dto.auditable;

import java.util.Date;

public class AuditableDTO {
    protected String createdBy;
    protected Date createdDate;
    protected String lastModifiedBy;
    protected Date lastModifiedDate;
}
