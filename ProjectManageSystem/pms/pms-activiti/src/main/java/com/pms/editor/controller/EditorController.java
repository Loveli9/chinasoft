package com.pms.editor.controller;

import com.pms.editor.service.EditorService;
import com.pms.enums.HttpCodeEnum;
import com.pms.exception.PMSException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/service")
@Api(description = "设计器的前端控制器")
public class EditorController {

    @Autowired
    EditorService editorService;

    @ApiOperation(value = "获取设计器页面的json", notes = "获取设计器页面的json")
    @GetMapping("/editor/stencilset")
    public Object getStencilset() {
       return editorService.getStencilset();
    }

    @ApiOperation(value = "根据modelId获取model", notes = "根据modelId获取model")
    @GetMapping(value = "/model/{modelId}/json")
    public Object getEditorJson(@PathVariable(value ="modelId" )  String modelId) {
        Optional.ofNullable(modelId).orElseThrow(() -> new PMSException(HttpCodeEnum.INVALID_REQUEST));
        return editorService.getEditorJson(modelId);
    }

    @ApiOperation(value = "保存model信息", notes = "保存model信息")
    @PutMapping("/model/{modelId}/save")
    public void saveModel(@PathVariable(value ="modelId" ) String modelId, String name, String description,
                          String json_xml, String svg_xml) {
        Optional.ofNullable(modelId).orElseThrow(() -> new PMSException(HttpCodeEnum.INVALID_REQUEST));
        editorService.saveModel(modelId, name, description, json_xml, svg_xml);
    }
}
