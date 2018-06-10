package com.example.mohamed.sochub.Comman;
import android.content.Context;

import com.example.mohamed.sochub.Entitis.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpClient {
    private static AsyncHttpClient client =new AsyncHttpClient();
    private static String BaseUrl="http://192.168.1.10:8080/statarter/myresource/";
    public static void Get( final CallBackjson Responss,String apiPath){
        client.get(BaseUrl+apiPath,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray Response){
                try{
                    if(!Response.get(0).toString().equals("error:Sign in")){
                        Responss.onResponse(Response);}

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                int x= statusCode;
            }
        });
    }
    public static void post(Context context,String url,JSONObject jsonParams,final CallBackPost Call,final Callback<Integer> callFileaure){
        StringEntity param= new StringEntity(jsonParams.toString(),cz.msebera.android.httpclient.protocol.HTTP.UTF_8);
        param.setContentType( new cz.msebera.android.httpclient.message.BasicHeader(cz.msebera.android.httpclient.protocol.HTTP.CONTENT_TYPE, "application/json"));
        client.post(context,BaseUrl+url, param, "application/json", new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Call.onResponse(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                callFileaure.onresponse();
            }
        });
    }
}
