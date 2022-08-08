package com.cos.photogramstart.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	
	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto <?> validationException(CustomValidationException e) {
		log.info("customvalidationException: " + e.getMessage());
		return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
	}
}
