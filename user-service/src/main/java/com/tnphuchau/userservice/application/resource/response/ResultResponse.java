package com.tnphuchau.userservice.application.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class ResultResponse<T>  {

    @JsonProperty("result")
    private T result;

    @JsonProperty("response")
    private BasicResponse response;
}
