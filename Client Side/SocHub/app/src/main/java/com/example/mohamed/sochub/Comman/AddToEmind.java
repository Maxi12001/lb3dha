package com.example.mohamed.sochub.Comman;

import android.content.Context;
import android.widget.Toast;

import com.example.mohamed.sochub.Entitis.CallBackPost;
import com.example.mohamed.sochub.Entitis.Callback;
import com.example.mohamed.sochub.Entitis.SessionInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class AddToEmind {
   public static void Remind(final Context context ,String aid){
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("aid",aid );
            jsonParams.put("uid", SessionInfo.getId());
            HttpClient.post(context, "Reminding/AddtoRemind", jsonParams, new CallBackPost() {
                @Override
                public void onResponse(byte[] responseBody) {
                    String y = null;
                    try {
                        y = new String(responseBody, "UTF-8");
                        JSONObject js = new JSONObject(y);
                        String x = js.getString("Massage");
                        Toast.makeText(context, y, Toast.LENGTH_SHORT).show();
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
