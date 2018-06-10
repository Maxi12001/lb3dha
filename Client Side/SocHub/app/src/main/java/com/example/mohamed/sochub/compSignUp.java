package com.example.mohamed.sochub;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class compSignUp extends AppCompatActivity {
    EditText codeText ;
    Button complate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_sign_up);
        codeText =(EditText) findViewById(R.id.Code);
        complate = (Button)findViewById(R.id.comp);
        complate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complateClicked();
            }
        });

    }
    public void msg(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }
    private void complateClicked(){
        String Code =codeText.getText().toString();
        if (Code.length()==5){
            JSONObject j =new JSONObject();
            try {
                j.put("Code",Code);
                HttpClient.post(this, "Signing/Complate", j, new CallBackPost() {
                    @Override
                    public void onResponse(byte[] responseBody) {

                        try {
                            String Y = new String(responseBody, "UTF-8");
                            JSONObject jsonobj = new JSONObject(Y);
                            String msg = jsonobj.getString("Massage");
                            if (msg.equals("Avtivate Complate")){
                                String Sid = jsonobj.get("SID").toString();
                                String name=jsonobj.get("name").toString();
                                String id=jsonobj.get("ID").toString();
                                SessionInfo.setSid(Sid);
                                SessionInfo.setId(id);
                                SessionInfo.setName(name);
                                SaveSession.saveCreadintiol(Sid,name,id,getApplicationContext());
                                openMain();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
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
        else{
            msg ("error");
        }
    }

    public void openMain(){
        Intent intent =new Intent(this,MainPage.class);
        startActivity(intent);
    }
}
