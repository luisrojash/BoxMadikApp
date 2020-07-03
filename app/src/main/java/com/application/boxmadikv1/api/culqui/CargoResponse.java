package com.application.boxmadikv1.api.culqui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CargoResponse {
    @SerializedName("object")
    @Expose
    private String mObject;
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("amount")
    @Expose
    private String mAmount;
    @SerializedName("amount_refunded")
    @Expose
    private String mAmount_refunded;
    @SerializedName("current_amount")
    @Expose
    private String mCurrent_amount;
    @SerializedName("installments")
    @Expose
    private String mInstallments;
    @SerializedName("installments_amount")
    @Expose
    private String mInstallments_amount;
    @SerializedName("currency")
    @Expose
    private String mCurrency;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("description")
    @Expose
    private String mDescription;

    public String getmObject() {
        return mObject;
    }

    public void setmObject(String mObject) {
        this.mObject = mObject;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getmAmount_refunded() {
        return mAmount_refunded;
    }

    public void setmAmount_refunded(String mAmount_refunded) {
        this.mAmount_refunded = mAmount_refunded;
    }

    public String getmCurrent_amount() {
        return mCurrent_amount;
    }

    public void setmCurrent_amount(String mCurrent_amount) {
        this.mCurrent_amount = mCurrent_amount;
    }

    public String getmInstallments() {
        return mInstallments;
    }

    public void setmInstallments(String mInstallments) {
        this.mInstallments = mInstallments;
    }

    public String getmInstallments_amount() {
        return mInstallments_amount;
    }

    public void setmInstallments_amount(String mInstallments_amount) {
        this.mInstallments_amount = mInstallments_amount;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(String mCurrency) {
        this.mCurrency = mCurrency;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
