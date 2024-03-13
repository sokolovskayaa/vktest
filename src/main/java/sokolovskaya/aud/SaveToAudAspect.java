package sokolovskaya.aud;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import sokolovskaya.service.AuditService;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class SaveToAudAspect {
    private final AuditService auditService;

    @Around("@annotation(sokolovskaya.aud.SaveToAudit)")
    public Object saveToAudit(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) Arrays.stream(joinPoint.getArgs()).filter(it -> it instanceof HttpServletRequest).findAny().orElse(null);
        if (request == null) return joinPoint.proceed();

        auditService.save(request);

        return joinPoint.proceed();
    }
}
