package restaurant_management_system.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.impl.BundleMessageService;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MessageHandle {

    private BundleMessageService  bundleMessageService;
    @Autowired
    public MessageHandle(BundleMessageService bundleMessageService)
    {
        this.bundleMessageService = bundleMessageService;
    }


    @ExceptionHandler
    public ResponseEntity<MessageResponse> messageHandle(Exception e)
    {
        return ResponseEntity.badRequest().body(bundleMessageService.getMessage(e.getMessage()));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<MessageResponse>> handelRunTimeException(MethodArgumentNotValidException exception)
    {

        List<MessageResponse> errors = new ArrayList<>();


        exception.getBindingResult().getFieldErrors().forEach
                (fieldError ->
                        {
                            String message = fieldError.getDefaultMessage();
                            errors.add(bundleMessageService.getMessage(message));
                        }
                );

        return ResponseEntity.badRequest().body(errors);
    }
}
