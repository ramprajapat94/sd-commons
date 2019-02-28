
package com.sdigitizers.utils.model.money;

import com.google.gson.reflect.TypeToken;
import com.sdigitizers.utils.fileh.JsonUtil;
import java.util.List;

/**
 *
 * @author Shriram Prajapat
 */
public class BankDetail {
    
    private String bankName;
    private String accountName;
    private String accountNumber;
    private String IFSC;
    private String branchName;
    private String UPIAddress;
    private String other;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    public String getUPIAddress() {
        return UPIAddress;
    }

    public void setUPIAddress(String UPIAddress) {
        this.UPIAddress = UPIAddress;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
    
    public String getCompleteDetails(){
        return bankName+", \n"+accountName+", \n"+accountNumber+", \n"+IFSC+", \n"+branchName+", \n"+UPIAddress;
    }
    
    public final String toJson(){
        return JsonUtil.GSON.toJson(this);
    }
    
    public static final BankDetail fromJSON(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<BankDetail>(){}.getType());
    }
    
    public static final List<BankDetail> fromJSONArray(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<List<BankDetail>>(){}.getType());
    }

    
}
