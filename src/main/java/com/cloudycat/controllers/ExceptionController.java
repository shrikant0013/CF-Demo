package com.cloudycat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by spandhare on 7/5/16.
 */

/**
 * Custom Exception handler
 *
 */

@ControllerAdvice
public class ExceptionController {
    private static final List<String> EXPOSABLE_FIELDS =
            Arrays.asList("timestamp", "message", "path");

    @Autowired
    private ErrorAttributes errorAttributes;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity foo(HttpServletRequest request) {
        Map<String, Object> errors = getErrorAttributes(request);
        errors.put("message", "Missing Pre or Post parameters");
        return ResponseEntity.badRequest().body(errors);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        final boolean WITHOUT_STACK_TRACE = true;
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(requestAttributes, WITHOUT_STACK_TRACE);

        attributes.keySet().removeIf(key -> !EXPOSABLE_FIELDS.contains(key));

        return attributes;
    }
}
