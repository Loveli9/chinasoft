package com.pms.base.controller;

import com.pms.constants.Setting;
import com.pms.utils.DateUtils;
import com.pms.utils.ExcelUtils;
import com.pms.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 2017/7/31.
 * <p>
 * Email 
 * <p>
 * Describe: base  Controller
 */
public class BaseController{

    protected Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * 导出信息
     * @param response   响应的请求
     * @param  excelTitle excel 标题
     * @param  arrayList 内容
     */
    public void ExceptInfo(HttpServletResponse response, String excelTitle, List<String[]> arrayList) throws ParseException {
        String excelFilePath = Setting.BASEFLODER+ "/"+Setting.EXCELADDRESS;

        Map<String, List<String[]>> map = new HashMap();//导出excel 内容
        map.put(excelTitle, arrayList);

        String nowDate = DateUtils.formatDate(new Date(),"yyyyMMddHHmmss");
        String fileLocal=  ExcelUtils.exportXlsExcel(map,excelFilePath,String.valueOf(System
                .currentTimeMillis()));
        FileUtils.downLoadFile(response,fileLocal,nowDate,false); //导出2003 excel
    }
}
