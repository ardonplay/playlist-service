package io.github.ardonplay.tactmuzik.playlistservice.controller.advice;

import static io.github.ardonplay.tactmuzik.playlistservice.util.UtilService.createErrorResponse;

import io.github.ardonplay.tactmuzik.playlistservice.exception.ErrorResponse;
import io.github.ardonplay.tactmuzik.playlistservice.exception.PlaylistNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private static final String LOG_STRING_TEMPLATE = "{}: {}";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlaylistNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleBoxNotFoundException(PlaylistNotFoundException exception) {
        log.info(LOG_STRING_TEMPLATE, exception.getCode(), exception.getMessage());
        return createErrorResponse(exception);
    }


}