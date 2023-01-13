package io.vdubovsky.licensemanagement.service.license;

import io.vdubovsky.licensemanagement.dto.LicenseStatusDto;
import jakarta.annotation.PostConstruct;
import javax0.license3j.License;
import javax0.license3j.io.LicenseReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class LicenseServiceImpl implements LicenseService {

    private License license;

    @PostConstruct
    public void init() {
        try (LicenseReader reader = new LicenseReader("license.bin")) {
            // Read license file
            License license = reader.read();
            if (!license.isOK(LicenseKeeper.getKey())) {
                throw new LicenseException("License is invalid");
            }
            this.license = license;
        } catch (IOException e) {
            throw new LicenseException("Error reading license file " + e);
        }
    }

    @Override
    public void validateLicense() {
        if (license == null) {
            throw new LicenseException("Error reading license file");
        }
        // Check clientId
        if (!LicenseKeeper.getClientGuid().equals(getClientId())) {
            throw new LicenseException("Wrong client id");
        }

        // Check expiration
        if (getExpireAt().isBefore(LocalDateTime.now())) {
            throw new LicenseException("License is expired, please upload the license");
        }
    }

    @Override
    public LicenseStatusDto getStatus() {
        return new LicenseStatusDto().setClientId(getClientId()).setExpireAt(getExpireAt());
    }

    private LocalDateTime getExpireAt() {
        String expireAtStr = license.get("expireAt").getString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime expireAt = LocalDateTime.parse(expireAtStr, formatter);
        return expireAt;
    }

    private UUID getClientId() {
        UUID clientId = license.get("clientId").getUUID();
        return clientId;
    }
}
