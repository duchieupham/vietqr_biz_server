package com.vietqr.org.dto.terminal;

public interface ITerminalResultOfFindDTO {
    String getName();

    String getAddress();

    String getNumOfStaff();

    /*
     * connect gRPC and update
     *
     * from bankId
     * inner join account_bank_receive to get bank_account, type
     * inner join bank_type to get bank_short_name, img_id
     *
     * private String getBankLogo;
     * private String getBankShortName;
     * private String getBankAccount;
     * */
    String getBankId();
}
