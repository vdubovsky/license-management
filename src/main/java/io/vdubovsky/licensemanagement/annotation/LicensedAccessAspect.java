package io.vdubovsky.licensemanagement.annotation;

import io.vdubovsky.licensemanagement.service.license.LicenseService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LicensedAccessAspect {

    private final LicenseService licenseService;

    @Before("@annotation(LicensedAccess)")
    public void validateLicense(JoinPoint joinPoint) throws Throwable {
       licenseService.validateLicense();
    }
}