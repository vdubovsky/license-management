package io.vdubovsky.licensemanagement.service.input;

import io.vdubovsky.licensemanagement.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
@Slf4j
@RequiredArgsConstructor
public class InputProcessorImpl implements InputProcessor {

    private final ExecutorService inputProcessorExecutorService;

    @Override
    public DeferredResult<OutputDto> processInput() {
        DeferredResult<OutputDto> result = new DeferredResult<>();
        inputProcessorExecutorService.submit(() -> doProcessInput(result));
        return result;
    }

    @SneakyThrows
    private void doProcessInput(DeferredResult<OutputDto> result) {
        Thread.sleep(2000);
        OutputDto outputDto = new OutputDto()
                .setRequestId(UUID.randomUUID())
                .setResponsePayload("PROCESSED");

        result.setResult(outputDto);
    }
}