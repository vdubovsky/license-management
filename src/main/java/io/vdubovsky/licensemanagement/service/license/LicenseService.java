package io.vdubovsky.licensemanagement.service.license;


import io.vdubovsky.licensemanagement.dto.LicenseStatusDto;

public interface LicenseService {
    void validateLicense();

    LicenseStatusDto getStatus();
}
