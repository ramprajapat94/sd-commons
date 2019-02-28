
package com.sdigitizers.utils.model.money;

import com.google.gson.reflect.TypeToken;
import com.sdigitizers.utils.fileh.JsonUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Shriram Prajapat
 */
public class MoneyAccount {
    
    private String nameId;
    private MoneyType moneyType;
    private String bankName;
    private String accountName;
    private String accountNumber;
    private String IFSC;
    private String branch;
    private String UPIAddress;
    private String description;
    private double openingBalance;
    private LocalDate openingBalanceDate;

    public MoneyAccount() {
    }
    
    public MoneyAccount(String name) {
        this.nameId = name;
    }
   
    public String getNameId() {
        return nameId;
    }

    public MoneyAccount setNameId(String nameId) {
        this.nameId = nameId;
        return this;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }
    
    public String getBankName() {
        return bankName;
    }

    public MoneyAccount setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public MoneyAccount setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public MoneyAccount setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getIFSC() {
        return IFSC;
    }

    public MoneyAccount setIFSC(String IFSC) {
        this.IFSC = IFSC;
        return this;
    }

    
    public String getBranch() {
        return branch;
    }

    public MoneyAccount setBranch(String branch) {
        this.branch = branch;
        return this;
    }
   
    
    public String getUPIAddress() {
        return UPIAddress;
    }

    public MoneyAccount setUPIAddress(String UPIAddress) {
        this.UPIAddress = UPIAddress;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MoneyAccount setDescription(String desc) {
        this.description = desc;
        return this;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public MoneyAccount setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
        return this;
    }
    
    public LocalDate getOpeningBalanceDate() {
        return openingBalanceDate;
    }

    public MoneyAccount setOpeningBalanceDate(LocalDate openingBalanceDate) {
        this.openingBalanceDate = openingBalanceDate;
        return this;
    }

    public static List<MoneyAccount> getAccounts(List<MoneyAccount> accs, PaymentMode pm){
        List<MoneyAccount> accounts = new ArrayList<>(accs);
        switch(pm){
            case CASH : {
                for(MoneyAccount ma : accs){
                    if(ma.getMoneyType() != MoneyType.HARD_MONEY)accounts.remove(ma);
                }
            }break;
            case CONTRA :
            case DEBIT_NOTE :
            case CREDIT_NOTE :
            case CUSTOMER_WALLET : {
                for(MoneyAccount ma : accs){
                    if(ma.getMoneyType() != MoneyType.NA)accounts.remove(ma);
                }
            }break;
            case PAYTM:
            case E_WALLET: {
                for(MoneyAccount ma : accs){
                    if(ma.getMoneyType() != MoneyType.E_WALLET_MONEY)accounts.remove(ma);
                }
            }break;
            default : for(MoneyAccount ma : accs){
                    if(ma.getMoneyType() != MoneyType.BANK_MONEY)accounts.remove(ma);
                }
        }
        return accounts;
    }

    @Override
    public String toString() {
        return nameId;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.nameId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MoneyAccount other = (MoneyAccount) obj;
        return this.nameId.equals(other.nameId);
    }        
    
    
    public final String toJson(){
        return JsonUtil.GSON.toJson(this);
    }
    
    public static final MoneyAccount fromJSON(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<MoneyAccount>(){}.getType());
    }
    
    public static final List<MoneyAccount> fromJSONArray(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<List<MoneyAccount>>(){}.getType());
    }
}
