package com.nadi.shopping.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseCommentsModel {

//    @SerializedName("comment_info")
//     private CommentsModel commentsModel;

    private String message;
    private boolean status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public CommentsModel getCommentsModel() {
//        return commentsModel;
//    }
//
//    public void setCommentsModel(CommentsModel commentsModel) {
//        this.commentsModel = commentsModel;
//    }
}
