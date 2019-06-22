package com.example.catchyourmoney;

public class reportItems {
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
}
