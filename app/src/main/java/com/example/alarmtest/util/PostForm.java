package com.example.alarmtest.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by mingchunhu on 2015/1/8.
 */
public class PostForm {
    private HttpClient mHttpClient;

    public void postUC(){
        mHttpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost("");
    }
}
