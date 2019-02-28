
package com.sdigitizers.utils.model.money;

import com.google.gson.reflect.TypeToken;
import com.sdigitizers.utils.util.MoneyUtil;
import com.sdigitizers.utils.fileh.JsonUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

/**
 *
 * @author Shriram Prajapat
 */
public class MoneyTransaction implements Comparable<MoneyTransaction>{
    
    private String transactionId;
    private LocalDate date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private PaymentMode paymentMode;
    private String moneyAccountName;
    private Currency currency;
    private String transactionReference;
    private String remarks;
    private String partyName;
    private String paymentFor;
    
    public String getTransactionId() {
        return transactionId;
    }

    public MoneyTransaction setTransactionId(String txnId) {
        this.transactionId = txnId;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public MoneyTransaction setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public MoneyTransaction setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyTransaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }

    public MoneyTransaction setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public MoneyTransaction setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public String getMoneyAccountName() {
        return moneyAccountName;
    }

    public MoneyTransaction setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
        return this;
    }

    
    public Currency getCurrency() {
        if(currency==null)currency = MoneyUtil.CURRENCY;
        return currency;
    }

    public MoneyTransaction setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public MoneyTransaction setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
        return this;
    }
    
    public String getRemarks() {
        return remarks;
    }

    public MoneyTransaction setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
    
    public String getPartyName() {
        return partyName;
    }

    public MoneyTransaction setPartyName(String partyName) {
        this.partyName = partyName;
        return this;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public MoneyTransaction setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
        return this;
    }

   @Override
    public int compareTo(MoneyTransaction o) {
       return getDate().compareTo(o.getDate());
    }
       
    
    public final String toJson(){
        return JsonUtil.GSON.toJson(this);
    }
    
    public static final List<MoneyTransaction> fromJSON(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<MoneyTransaction>(){}.getType());
    }


}
