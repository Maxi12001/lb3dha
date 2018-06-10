package com.example.mohamed.sochub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Comman.SaveSession;
import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;;
import org.json.JSONArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import com.example.mohamed.sochub.Entitis.*;

import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private Button signIn;
    private EditText Email,Pass;
    private TextView _newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email =(EditText)findViewById(R.id.email);
        Pass =(EditText)findViewById(R.id.password);
        signIn=(Button) findViewById(R.id.signinbutton);
        _newAccount=(TextView)findViewById(R.id.signUp);
        _newAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUp();
            }
        });
       signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                StartSignIn();
            }
        });
    }


    public void openSignUp(){
        Intent intent =new Intent(this,signUp.class);
        startActivity(intent);
    }
    public void StartSignIn(){
        JSONObject jsonParams= new JSONObject();
        try {
            int z;
            jsonParams.put("email",Email.getText().toString());
            jsonParams.put("pass",Pass.getText().toString());
            HttpClient.post(this, "Signing/signIn", jsonParams, new CallBackPost() {
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
                            SaveSession.saveCreadintiol(Sid,id,name,getApplicationContext());
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
        Intent intent =new Intent(this,MainPage.class);
        startActivity(intent);
    }
    public void erroMasage(){
        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
    }}
