package io.vdubovsky.licensemanagement.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorResponseDto {
    private String message;
}