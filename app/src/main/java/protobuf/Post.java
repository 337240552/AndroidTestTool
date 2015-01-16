package protobuf;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingchunhu on 2014/12/13.
 */
public class Post {
    private static final String TAG = "Post";

    public static void postFbHot() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httpost = new HttpPost("http://news.jiecao.fm/client/article/fhot.pb");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            //提交两个参数及值
            nvps.add(new BasicNameValuePair("hot_grade", "0"));
            nvps.add(new BasicNameValuePair("is_after", "true"));
            nvps.add(new BasicNameValuePair("types", "1,2"));
            nvps.add(new BasicNameValuePair("size", "20"));
            //设置表单提交编码为UTF-8
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            HttpResponse response = httpclient.execute(httpost);
            Log.d(TAG, "Login form get: " + response.getStatusLine());

            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            byte[] buf = new byte[1024];
            int n  = -1;
            StringBuilder sb = new StringBuilder();
            while ((n = inputStream.read(buf)) != -1) {
               Log.d(TAG, new String(buf));
            }
            entity.consumeContent();
        } catch (Exception e) {
            Log.d(TAG, "e:" + e.getMessage(), e);
        }finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
}
