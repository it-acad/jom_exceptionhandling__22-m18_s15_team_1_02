package com.softserve.itacademy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleNullEntityReferenceException(HttpServletRequest request, NullEntityReferenceException ex) {
        return createModelAndView(request, HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
        return createModelAndView(request, HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        return createModelAndView(request, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private static ModelAndView createModelAndView(HttpServletRequest request, HttpStatus httpStatus, Exception ex) {
        log.error("An error occurred: \"{}\" at the specified URL: \"{}\".", ex.getMessage(), request.getRequestURI());
        ModelAndView modelAndView = new ModelAndView(getView(httpStatus));
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    private static String getView(HttpStatus httpStatus) {
        switch (httpStatus) {
            case BAD_REQUEST: return "400";
            case NOT_FOUND: return "404";
            default: return "500";
        }
    }
}