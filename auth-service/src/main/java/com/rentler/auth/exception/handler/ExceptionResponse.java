package com.rentler.auth.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;

    @JsonIgnore
    private String timeStamp;

    @JsonIgnore
    private String trace;

    @JsonIgnore
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ExceptionResponse(Map<String, Object> errorAttributes) {
        this.setPath((String) errorAttributes.get("path"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTimeStamp(errorAttributes.get("timestamp").toString());
        this.setTrace((String) errorAttributes.get("trace"));
    }
}
