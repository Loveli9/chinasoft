package com.pms.dto;

import lombok.Data;

import java.util.Map;


@Data
public class TaskDTO {
    private String applicant;
    private String taskId;
    private String taskName;
    private String title;
    private String pdName;
    private String version;
    private Long time;
    private String processInstanceId;
    private String status;
    private Variable variable;
    private String nodeKey;
    private String processDefKey;
    private String category;
    public void setVariable(Map<String, Object> variable) {
        this.variable = new Variable(variable);
    }
}
