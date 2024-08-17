package pdp.gg_store.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {


    private boolean success;

    private T data;

    private String errorMsg;



    public static<T> ApiResult<T> success(T data){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        apiResult.setData(data);
        return apiResult;
    }

    public static<T> ApiResult<T> error(String errorMsg){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setErrorMsg(errorMsg);
        return apiResult;
    }


    }




