package io.vdubovsky.licensemanagement.rest.input;

import io.vdubovsky.licensemanagement.dto.OutputDto;
import org.springframework.web.context.request.async.DeferredResult;

public interface InputProcessor {

    DeferredResult<OutputDto> processInput();
}
