package com.pms.aspect;

import com.alibaba.fastjson.JSON;
import com.pms.annotation.SLog;
import com.pms.dao.PMSLogDao;
import com.pms.entity.PMSLog;
import com.pms.shiro.ShiroUser;
import com.pms.shiro.PMSSysUser;
import com.pms.utils.ApplicationContextRegister;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


public class SaveLogTask implements Runnable {


    private PMSLogDao pmsLogDao = ApplicationContextRegister.getBean(PMSLogDao.class);

    private ProceedingJoinPoint joinPoint;
    private long time;
    private String ip;

    public SaveLogTask(ProceedingJoinPoint point, long time, String ip) {
        this.joinPoint = point;
        this.time = time;
        this.ip = ip;
    }

    @Override
    public void run() {
        saveLog(joinPoint, time, ip);
    }

    /**
     * 保存日志 到数据库
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time, String ip) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        PMSLog pmsLog = new PMSLog();
        SLog sLog = method.getAnnotation(SLog.class);

        if (pmsLog != null) {
            // 注解上的描述
            pmsLog.setMsg(sLog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        pmsLog.setSrc(className + "." + methodName + "()");

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSON.toJSONString(args[0]);
            pmsLog.setParams(params);
        } catch (Exception e) {

        }

        // 设置IP地址
        pmsLog.setIp(ip);
        // 用户名
        ShiroUser currUser = PMSSysUser.ShiroUser();

        if (null == currUser) {
            if (null != pmsLog.getParams()) {
                pmsLog.setName(pmsLog.getParams());
                pmsLog.setLoginName(pmsLog.getParams());
            } else {
                pmsLog.setName("获取用户信息为空");
                pmsLog.setLoginName("获取用户信息为空");
                pmsLog.setCreateId(-1L);
            }
        } else {
            pmsLog.setName(currUser.getName());
            pmsLog.setLoginName(currUser.getUsername());

        }

        pmsLog.setUseTime(time);


        // 保存系统日志
        pmsLogDao.insert(pmsLog);
    }
}
