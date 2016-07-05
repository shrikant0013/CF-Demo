package com.cloudycat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by spandhare on 7/1/16.
 */

@RestController
public class ConcatController {

    private static final String template = "%s%s";
    @RequestMapping("/concat")
    public String concatenation(@RequestParam(value="pre") String pre,
                             @RequestParam(value="post") String post) {
        return String.format(template, pre, post);
    }
}