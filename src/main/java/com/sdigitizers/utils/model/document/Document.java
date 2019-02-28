
package com.sdigitizers.utils.model.document;

import java.time.LocalDate;

/**
 *
 * @author Shriram Prajapat
 */
public class Document {
    
    private int id;
    private String fileUrl;
    private DocumentType type;
    private boolean required;
    private LocalDate submissionDate;
    private LocalDate verificationDate;
    private String verifiedBy;
    private boolean renewalRequired;
    private LocalDate expiryDate;
    
    
    
    public boolean isSubmitted(){
        return null != submissionDate;
    }
    public boolean isVerified(){
        return null != verificationDate;
    }
    public boolean isExpiryDateSet(){
        return null != expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public LocalDate getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDate verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public boolean isRenewalRequired() {
        return renewalRequired;
    }

    public void setRenewalRequired(boolean renewalRequired) {
        this.renewalRequired = renewalRequired;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
}
