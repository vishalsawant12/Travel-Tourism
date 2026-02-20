package com.example.TravelAndTourism.responsewrapper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseWrapper {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String message;
    private Object data;

}
