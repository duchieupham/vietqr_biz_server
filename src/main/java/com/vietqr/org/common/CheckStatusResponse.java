package com.vietqr.org.common;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import org.springframework.http.HttpStatus;

public class CheckStatusResponseUtil {
    public static HttpStatus checkStatusResponseMessageDTO(ResponseMessageDTO responseMessageDTO) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (Status.SUCCESS.equals(responseMessageDTO.getStatus())) {
            httpStatus = HttpStatus.OK;
        }
        return httpStatus;
    }

    public static HttpStatus checkStatusResponseObjectDTO(ResponseObjectDTO responseObjectDTO) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (Status.SUCCESS.equals(responseObjectDTO.getStatus())) {
            httpStatus = HttpStatus.OK;
        }
        return httpStatus;
    }
}
