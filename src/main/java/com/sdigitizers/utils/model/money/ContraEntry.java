
package com.sdigitizers.utils.model.money;

import com.google.gson.reflect.TypeToken;
import com.sdigitizers.utils.fileh.JsonUtil;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Shriram Prajapat
 */
public class ContraEntry {

  private int id;
  private LocalDate date;
  private String fromMoneyAccount;
  private String toMoneyAccount;
  private double amount;
  private String transactionReference;
  private String remarks;

    public String getId() {
        return String.format("%06d", id);
    }
    
    public int getIdAsInt() {
        return id;
    }

    public ContraEntry setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public ContraEntry setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getFromMoneyAccount() {
        return fromMoneyAccount;
    }

    public ContraEntry setFromMoneyAccount(String fromMoneyAccount) {
        this.fromMoneyAccount = fromMoneyAccount;
        return this;
    }

    public String getToMoneyAccount() {
        return toMoneyAccount;
    }

    public ContraEntry setToMoneyAccount(String toMoneyAccount) {
        this.toMoneyAccount = toMoneyAccount;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public ContraEntry setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public ContraEntry setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public ContraEntry setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
  
    
    public final String toJson(){
        return JsonUtil.GSON.toJson(this);
    }
    
    public static final ContraEntry fromJSON(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<ContraEntry>(){}.getType());
    }
    
    public static final List<ContraEntry> fromJSONArray(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<List<ContraEntry>>(){}.getType());
    }
  
}
