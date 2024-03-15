package com.oasis.taskmanagement.exception;

import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex){

        if (ex instanceof DataValidationException validationException) {
            return handleValidationException(validationException);
        } else if (ex instanceof ResourceNotFoundException resourceNotFound) {
            return handleResourceNotFoundException(resourceNotFound);
        } else if (ex instanceof AuthenticationException authenticationEx) {
            return handleAuthenticationException(authenticationEx);
        } else if (ex instanceof org.springframework.security.core.AuthenticationException authorizationEx) {
            return handleUnAuthorizedException(authorizationEx);
        } else if (ex instanceof MethodArgumentNotValidException methodArgsEx) {
            return handleMethodArgumentException(methodArgsEx);
        } else if (ex instanceof DataAccessException dataAccessException) {
            return handleDataAccessException(dataAccessException);
        }
        return  ResponseEntity
                .internalServerError()
                .body(new ApiResponse<>( ex.getMessage(), false,null));
    }

    private ResponseEntity<ApiResponse<String>> handleDataAccessException(DataAccessException ex) {
        logger.error(ex.getMessage());
        return  ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(ex.getMessage(), false,null));
    }

    private ResponseEntity<ApiResponse<String>> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        return  ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(ex.getMessage(), false,null));
    }

    private ResponseEntity<ApiResponse<String>> handleValidationException(DataValidationException ex){
        logger.error(ex.getMessage());
        return  ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(ex.getMessage(), false,null));
    }

    private ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex){
        logger.error(ex.getMessage());
        return  ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(ex.getMessage(), false,null));
    }


    private ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex){
        logger.error(ex.getMessage());
        return  ResponseEntity
                .status(403)
                .body(new ApiResponse<>(ex.getMessage(), false,null));
    }

    private ResponseEntity<ApiResponse<String>> handleUnAuthorizedException(org.springframework.security.core.AuthenticationException ex){
        logger.error(ex.getMessage());
        return  ResponseEntity
                .status(401)
                .body(new ApiResponse<>(ex.getMessage(), false,null));
    }

}
