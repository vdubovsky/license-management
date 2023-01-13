package io.vdubovsky.licensemanagement.rest.controller;

import io.vdubovsky.licensemanagement.dto.OutputDto;
import io.vdubovsky.licensemanagement.service.input.InputProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/input")
public class InputController {

    private final InputProcessor inputProcessor;

    public InputController(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    @GetMapping
    public DeferredResult<OutputDto> entrypoint(){
        return inputProcessor.processInput();
    }
}
