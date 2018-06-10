package com.example.mohamed.sochub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Comman.SaveSession;
import com.example.mohamed.sochub.Entitis.CallBackPost;
import com.example.mohamed.sochub.Entitis.CallBackjson;
import com.example.mohamed.sochub.Entitis.Callback;
import com.example.mohamed.sochub.Entitis.SessionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
      // SaveSession.deletefile(getApplicationContext());
        int i = SaveSession.readCreadintiol(getApplicationContext());
        if (i != 1){
            openMain(0);
        }
        else {
            JSONObject obj= new JSONObject();
            try {
                obj.put("Sid", SessionInfo.getSid());
                obj.put("Uid",SessionInfo.getId());
                HttpClient.post(getApplicationContext(), "Signing/CheckSession", obj, new CallBackPost() {
                    @Override
                    public void onResponse(byte[] responseBody) {
                        try {
                            String Y = new String(responseBody, "UTF-8");
                            JSONObject j= new JSONObject(Y);
                            String x =j.getString("Active State");
                            if(!x.equals("1")){
                                openMain(0);
                                SaveSession.deletefile(getApplicationContext());
                                }
                            else{
                                openMain(1);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Callback<Integer>() {
                    @Override
                    public void onresponse() {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void openMain(int mode){
        Intent nIntent = mode ==0 ?new Intent(this,MainActivity.class):new Intent(this,MainPage.class);
        startActivity(nIntent);

    }

}
