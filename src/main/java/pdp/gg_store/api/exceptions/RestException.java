package pdp.gg_store.api.exceptions;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {

    private String message;

    public RestException(String message) {
        this.message = message;
    }


    public static RestException alreadyExist(String key){
        return new RestException(key + " already exists");
    }


}
