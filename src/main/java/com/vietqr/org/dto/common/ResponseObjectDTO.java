package com.vietqr.org.dto.common;

public class ResponseObjectDTO {
    private String status;
    private Object data;

    public ResponseObjectDTO() {
    }

    public ResponseObjectDTO(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
