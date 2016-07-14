package com.wang.chat.model.network.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wang on 16-7-4.
 */
public class Contributor {
    @SerializedName("login")
    public String login;
    @SerializedName("contributions")
    public int contributions;
}
