package com.qufenqi.controller.MyAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by liuxin on 16/9/10.
 * <p>
 * <p>
 * 1, execution(modifier-pattern?
 * ret-type-pattern declaring-type-pattern?
 * name-pattern(param-pattern) throws-pattern?)
 * pattern分别表示
 * 修饰符匹配（modifier-pattern?）、
 * 返回值匹配（ret-type-pattern）、
 * 类路径匹配（declaring-type-pattern?）、
 * 方法名匹配（name-pattern）、
 * 参数匹配（(param-pattern)）、
 * 异常类型匹配（throws-pattern?），
 * 其中后面跟着“?”的是可选项。
 * <p>
 * 2,  注解
 * @Before         前置通知（Before advice） ：            在某连接点（JoinPoint）之前执行的通知，但这个通知不能阻止连接点前的执行。
 * @After          后通知（After advice） ：               当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
 * @AfterReturning 返回后通知（After return advice） ：     在某连接点正常完成后执行的通知，不包括抛出异常的情况。
 * @Around         环绕通知（Around advice） ：            包围一个连接点的通知，类似Web中Servlet规范中的Filter的doFilter方法。
 *                                                     可以在方法的调用前后完成自定义的行为，也可以选择不执行。
 * @AfterThrowing  抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
 */
@Component
@Aspect
public class MonitorLogAspect {

    //@Pointcut("execution(* com.qufenqi.controller.TestController.getData(java.lang.Integer)) && args(i)")
    //@Pointcut("execution(* com.qufenqi.controller.TestController..*(..))")
    @Pointcut("@annotation(com.qufenqi.controller.MyAnnotation.MonitorLog)")
    public void pointcut() {

    }

    //@Around("execution(* com.abc.service.*.many*(..))")
    @Around("pointcut() && @annotation(MonitorLog)")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            args[0] = "改变后的参数1";
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return "原返回值：" + returnValue + "，这是返回结果的后缀";
    }


    @Before("pointcut() && @annotation(monitorLog)")
    public void doBefore(ProceedingJoinPoint point, MonitorLog monitorLog) {
        System.out.println("*********startMonitorLog*********");
        //point.proceed()
        /*
         MonitorLogUtil.defaultInit("/promotion/subvention/queryEnrollCommunity",
                                     ObjectUtil.pToString(userId));
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        */
        String[] param = monitorLog.param();
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String[] args = {};
        for (String key : param) {
            Object attribute = session.getAttribute(key);

        }
        System.out.println(monitorLog);
    }

    @AfterThrowing(pointcut = "pointcut() && @annotation(monitorLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, MonitorLog monitorLog, Throwable e) {
        System.out.println("*********捕获异常开始*********");
        e.printStackTrace();
        System.out.println("*********捕获异常结束*********");
        /*
        if(e instance of BuniessException){
            MonitorLogUtil.finshError(e.getErrorCode(), e.getChineseMessage(), null);
        }else if(e instance of FailException){
            MonitorLogUtil.finshFail(JSON.toJSONString(e.getResp()));
        }else{
            MonitorLogUtil.finshError(XKHResponseCodeEnum.SYSTEM_ERROR.getCode(),
                    XKHResponseCodeEnum.SYSTEM_ERROR.getChineseMessage(), null);
        }
         */

    }

    @AfterReturning(value = "pointcut() && @annotation(monitorLog)", returning = "returnValue")
    public void doAfterReturning(JoinPoint jp, MonitorLog monitorLog, Object returnValue) {
        System.out.println("*********endMonitorLog*********");
       /*
        MonitorLogUtil.finshSuccess(JSON.toJSONString(promSubventionActivityVo));
        System.out.println("@AfterReturning|returnValue: " + returnValue);
        */
    }

}
