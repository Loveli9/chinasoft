package com.pms.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class StartTaskForm {
    @NotEmpty
    public String procDefKey;
//    @NotEmpty
    public String entityId;
    @NotEmpty
    public String title;
//    public String userId;
    public String comment;
}
