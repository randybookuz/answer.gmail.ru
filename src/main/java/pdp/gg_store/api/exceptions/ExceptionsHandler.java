package pdp.gg_store.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<String>>handler(Exception e){
       ApiResult apiResult=new ApiResult<>(false,"Exceptions:",e.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResult);
    }
}
