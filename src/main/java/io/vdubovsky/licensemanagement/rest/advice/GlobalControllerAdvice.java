package io.vdubovsky.licensemanagement.rest.advice;

import io.vdubovsky.licensemanagement.dto.ErrorResponseDto;
import io.vdubovsky.licensemanagement.service.license.LicenseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(LicenseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ErrorResponseDto licenseExceptionHandled(LicenseException ex) {
        String message = ex.getMessage();
        return new ErrorResponseDto().setMessage(message);
    }
}