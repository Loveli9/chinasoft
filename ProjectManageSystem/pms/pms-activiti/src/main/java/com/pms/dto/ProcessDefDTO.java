package com.pms.dto;

import lombok.Data;


@Data
public class ProcessDefDTO {

    public String category;
    public String processonDefinitionId;
    public String key;
    public String name;
    public Integer revision;
    public Long deploymentTime;
    public String xmlName;
    public String picName;
    public String deploymentId;
    public Boolean suspend;
    public String description;
}
