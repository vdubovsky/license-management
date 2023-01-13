package io.vdubovsky.licensemanagement.rest.controller;

import io.vdubovsky.licensemanagement.dto.LicenseStatusDto;
import io.vdubovsky.licensemanagement.service.license.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    public ResponseEntity<LicenseStatusDto> getLicenseStatus() {
        return ResponseEntity.ok(licenseService.getStatus());
    }
}
