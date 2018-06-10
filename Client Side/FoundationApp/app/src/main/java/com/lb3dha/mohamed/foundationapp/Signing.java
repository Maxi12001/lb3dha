package com.lb3dha.mohamed.foundationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lb3dha.mohamed.foundationapp.Common.HttpClient;
import com.lb3dha.mohamed.foundationapp.Common.SaveSession;
import com.lb3dha.mohamed.foundationapp.Entitis.CallBackPost;
import com.lb3dha.mohamed.foundationapp.Entitis.Callback;
import com.lb3dha.mohamed.foundationapp.Entitis.SessionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Signing extends AppCompatActivity {
    private Button signIn;
    private EditText Email,Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);
        Email =(EditText)findViewById(R.id.email);
        Pass =(EditText)findViewById(R.id.password);
        signIn=(Button) findViewById(R.id.signinbutton);
        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                StartSignIn();
            }
        });

    }

    public void StartSignIn(){
        JSONObject jsonParams= new JSONObject();
        try {
            int z;
            jsonParams.put("email",Email.getText().toString());
            jsonParams.put("pass",Pass.getText().toString());
            HttpClient.post(this, "Signing/signInAdmin", jsonParams, new CallBackPost() {
                @Override
                public void onResponse(byte[] responseBody) {
                    try {
                        String Y = new String(responseBody, "UTF-8");
                        if(!Y.equals("error:notFound")){
                            JSONArray jsonArr = new JSONArray(Y);
                            String Sid = jsonArr.getJSONObject(1).get("SID").toString();
                            String name=jsonArr.getJSONObject(0).get("name").toString();
                            String id=jsonArr.getJSONObject(0).get("ID").toString();
                            SessionInfo.setSid(Sid);
                            SessionInfo.setId(id);
                            SessionInfo.setName(name);
                            SaveSession.saveCreadintiol(Sid,name,id,getApplicationContext());
                            openApeal();}
                        else{
                            erroMasage();
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
    public void openApeal(){
        Intent intent =new Intent(this,Signing.class);
        startActivity(intent);
    }
    public void erroMasage(){
        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
    }


}
