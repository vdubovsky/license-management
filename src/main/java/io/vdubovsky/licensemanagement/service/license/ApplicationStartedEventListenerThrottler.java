package io.vdubovsky.licensemanagement.service.license;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartedEventListenerThrottler implements ApplicationListener<ApplicationStartedEvent> {

    private final LicenseService licenseService;
    private final ConfigurableApplicationContext context;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            licenseService.validateLicense();
        } catch (LicenseException lx) {
            log.error("INVALID LICENSE, APP WILL BE CLOSED IN 60 MIN, ERROR: '{}'", lx.getMessage());
            Executors.newSingleThreadScheduledExecutor().schedule(
                    () -> System.exit(SpringApplication.exit(context, () -> 0)), 60, TimeUnit.MINUTES);
        }
    }
}
