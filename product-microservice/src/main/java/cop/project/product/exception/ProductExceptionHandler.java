package cop.project.product.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cop.project.product.model.ErrorResponse;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(final UserNotFoundException notFoundException) {

        List<String> errorList = new ArrayList<>();
        errorList.add(notFoundException.getMessage());

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.toString(), errorList);

        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }

}
