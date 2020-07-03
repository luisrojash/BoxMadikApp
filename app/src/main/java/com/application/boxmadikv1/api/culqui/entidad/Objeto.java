package com.application.boxmadikv1.api.culqui.entidad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Objeto {
    @SerializedName("object")
    @Expose
    private String mObject ;
    @SerializedName("id")
    @Expose
    private String mId ;
    @SerializedName("type")
    @Expose
    private String mType ;
    @SerializedName("email")
    @Expose
    private String mEmail ;

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

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
