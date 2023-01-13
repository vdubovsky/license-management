package io.vdubovsky.licensemanagement.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class OutputDto {
    private UUID requestId;
    private String requestPayload;
    private String responsePayload;
}
