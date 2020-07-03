package com.application.boxmadikv1.api.culqui;

import com.application.boxmadikv1.api.culqui.entidad.Objeto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class IntegracionResponse {
    @SerializedName("object")
    @Expose
    private String mObject ;
    @SerializedName("id")
    @Expose
    private String mId ;
    @SerializedName("type")
    @Expose
    private String mType ;
    @SerializedName("creation_date")
    @Expose
    private String mCreation_Date ;
    @SerializedName("card_number")
    @Expose
    private String mCard_Number ;
    @SerializedName("last_four")
    @Expose
    private String mLast_four ;
    @SerializedName("active")
    @Expose
    private String mActive ;

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

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmCreation_Date() {
        return mCreation_Date;
    }

    public void setmCreation_Date(String mCreation_Date) {
        this.mCreation_Date = mCreation_Date;
    }

    public String getmCard_Number() {
        return mCard_Number;
    }

    public void setmCard_Number(String mCard_Number) {
        this.mCard_Number = mCard_Number;
    }

    public String getmLast_four() {
        return mLast_four;
    }

    public void setmLast_four(String mLast_four) {
        this.mLast_four = mLast_four;
    }

    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }
}
