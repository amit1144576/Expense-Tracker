package com.example.catchyourmoney.reports;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class reportItems implements Serializable {

    private int mImageResource;
    private String mTextType, mTextCateg, mTextMethod, mTextAmount, mTextDate, mTextRecurr, mTextComment, mId;

    public reportItems(int imageResource, String textType, String textCateg, String textMethod, String textAmount, String textDate,
                       String textRecurr, String textComment, String Id){
        mImageResource = imageResource;
        mTextType = textType;
        mTextCateg = textCateg;
        mTextMethod = textMethod;
        mTextAmount = textAmount;
        mTextDate = textDate;
        mTextRecurr = textRecurr;
        mTextComment = textComment;
        mId = Id;
    }

    public String getTextMethod() {
        return mTextMethod;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getTextAmount() {
        return mTextAmount;
    }

    public String getTextDate() {
        return mTextDate;
    }

    public String getTextRecurr() {
        return mTextRecurr;
    }

    public String getTextComment() {
        return mTextComment;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getTextType() {
        return mTextType;
    }

    public String getTextCateg() {
        return mTextCateg;
    }

    protected reportItems(Parcel in) {

    }

 /*   @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<reportItems> CREATOR = new Parcelable.Creator<reportItems>() {
        @Override
        public reportItems createFromParcel(Parcel in) {
            return new reportItems(in);
        }

        @Override
        public reportItems[] newArray(int size) {
            return new reportItems[size];
        }
    };
*/
    @Override
    public String toString() {
        return "reportItems{" +
                "mImageResource=" + mImageResource +
                ", mTextType='" + mTextType + '\'' +
                ", mTextCateg='" + mTextCateg + '\'' +
                ", mTextMethod='" + mTextMethod + '\'' +
                ", mTextAmount='" + mTextAmount + '\'' +
                ", mTextDate='" + mTextDate + '\'' +
                ", mTextRecurr='" + mTextRecurr + '\'' +
                ", mTextComment='" + mTextComment + '\'' +
                ", mId='" + mId + '\'' +
                '}';
    }
}
