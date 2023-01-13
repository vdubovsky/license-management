package io.vdubovsky.licensemanagement.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class LicenseStatusDto {
    private LocalDateTime expireAt;
    private UUID clientId;
}
