package com.example.microriot.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String requestId ="";
    private Status status = new Status();
}
