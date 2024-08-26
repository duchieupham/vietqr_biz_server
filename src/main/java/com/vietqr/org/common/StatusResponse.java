package com.vietqr.org.common;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import org.springframework.http.HttpStatus;

public class StatusResponse {
    public static HttpStatus getStatusResponseMessage(ResponseMessageDTO responseMessageDTO) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (Status.SUCCESS.equals(responseMessageDTO.getStatus())) {
            httpStatus = HttpStatus.OK;
        }
        return httpStatus;
    }

    public static HttpStatus getStatusResponseObject(Object object) {
        HttpStatus httpStatus = HttpStatus.OK;
        if(object instanceof ResponseMessageDTO){
            httpStatus =  HttpStatus.BAD_REQUEST;
        }
        return httpStatus;
    }
}
