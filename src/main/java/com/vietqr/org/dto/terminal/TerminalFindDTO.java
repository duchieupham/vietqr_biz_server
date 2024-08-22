package com.vietqr.org.dto.terminal;

import org.springframework.boot.context.properties.bind.DefaultValue;

public class TerminalFindDTO {
    /*
    * Search by
    * private String name;
    * private String code;
    * private String address;
    * private String rawCode;
    * private String bankId;
    * */

    private String mid;

    /*
    * Type
    * 0: name
    * 1: code
    * 2: raw code
    * 3: address
    * 4: bank id
    * 5: other
    * */
    private byte type;

    private String searchTerm;

    public TerminalFindDTO() {
    }

    public TerminalFindDTO(String mid, byte type, String searchTerm) {
        this.mid = mid;
        this.type = type;
        this.searchTerm = searchTerm;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
