package com.apress.com.apress.handler;



import com.apress.com.apress.error.ErrorDetail;
import com.apress.com.apress.error.ValidationError;
import com.apress.com.apress.exception.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

    ErrorDetail errorDetail = new ErrorDetail();
    errorDetail.setTimestamp( new Date(  ).getTime() );
    errorDetail.setStatus( status.value() );
    errorDetail.setTitle( "Message Not Readable" );
    errorDetail.setDetail( ex.getMessage() );
    errorDetail.setDeveloperMessage( ex.getClass().getName() );

    return handleExceptionInternal( ex,errorDetail,headers,status,request );

    }
  @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp( new Date(  ).getTime() );
        errorDetail.setStatus( status.value() );
        errorDetail.setDetail( manve.getMessage() );
        errorDetail.setDeveloperMessage( manve.getClass().getName() );

        return handleExceptionInternal( manve,errorDetail,headers,status,request );
    }


//@ResponseStatus(HttpStatus.BAD_REQUEST)
//public @ResponseBody ErrorDetail handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
//
//
//    //public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
//    ErrorDetail errorDetail = new ErrorDetail();
//
////Populate errorDetail instance
//
//    errorDetail.setTimestamp( new Date().getTime() );
//    errorDetail.setStatus( HttpStatus.BAD_REQUEST.value() );
//    String requestPath = (String) request.getAttribute( "javax.servlet.error.request_uri" );
//    if (request == null) {
//        requestPath = request.getRequestURI();
//
//
//    }
//
//    errorDetail.setTitle( "Validation Failed" );
//    errorDetail.setDetail( "Input validation failed" );
//    errorDetail.setDeveloperMessage( manve.getClass().getName() );
//
//    //ValidationError instances
//
//    List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//    for (FieldError fe : fieldErrors) {
//
//        List<ValidationError> validationErrorList = errorDetail.getErrors().get( fe.getField() );
//        if (validationErrorList == null) {
//
//            validationErrorList = new ArrayList<ValidationError>();
//            errorDetail.getErrors().put( fe.getField(), validationErrorList );
//        }
//
//        ValidationError validationError = new ValidationError();
//        validationError.setCode( fe.getCode() );
//        validationError.setMessage( fe.getDefaultMessage() );
//        validationErrorList.add( validationError );
//    }
//        return errorDetail;
//
//}

}
