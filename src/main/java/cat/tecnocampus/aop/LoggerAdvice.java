package cat.tecnocampus.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Pointcut("execution(* cat.tecnocampus.useCases.UserUseCases.*(..))")
    public void pointcutAllMethods() { }

    @Before("pointcutForAllMethods()")
    public void beforeAllMethods() {
        logger.info("Working with a classroom");
    }

    @Pointcut("execution(* cat.tecnocampus.useCases.UserUseCases.find*(..))")
    public void pointcutFind() {}

    @After("pointcutBeginWordFind()")
    public void afterBeginWordFind() {
        logger.info("Finding classrooms");
    }

    @Pointcut("execution(* cat.tecnocampus.useCases.UserUseCases.insertBatch(..))")
    public void pointcutInsertBatch() { }

    @Around("pointcutInsertBatch()")
    public int[] dealRequestParam(ProceedingJoinPoint jp) {

        try {
            logger.info("before multiple inser");
            int[] res = (int[]) jp.proceed();
            logger.info("after multiple insert");
            return res;
        } catch (Throwable throwable) {
            logger.error("Showing notes: Something went wrong");
            throwable.printStackTrace();
            return new int[]{};
        }
    }
}
