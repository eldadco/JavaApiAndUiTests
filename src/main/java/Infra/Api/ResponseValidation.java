package Infra.Api;
import lombok.Data;


public @Data
class ResponseValidation<T> {
    private Boolean statusCodeResult;
    private T bodyResult;
}
