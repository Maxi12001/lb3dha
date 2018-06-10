package com.example.mohamed.sochub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Entitis.CallBackPost;
import com.example.mohamed.sochub.Entitis.CallBackjson;
import com.example.mohamed.sochub.Entitis.CallBackobj;
import com.example.mohamed.sochub.Entitis.Callback;
import com.example.mohamed.sochub.Entitis.SessionInfo;
import com.loopj.android.http.AsyncHttpClient;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class newApeal2 extends AppCompatActivity  {
    protected Button publish;
    protected EditText editText,title,brief;
    public JSONArray Category;
    public String catID;
    public ArrayList<String> Cat=new ArrayList<>();
    public Spinner catSpine ;
    public HashMap<String,String>CatID=new HashMap<>();
    private AsyncHttpClient client =new AsyncHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_apeal2);
       publish =(Button)findViewById(R.id.Publish);
        editText =(EditText) findViewById(R.id.ApealText);
        title=(EditText)findViewById(R.id.title);
        brief=(EditText)findViewById(R.id.brieft);
        catSpine=(Spinner)findViewById(R.id.Catlog) ;
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                for(int i=0;i<j.length();i++){
                    try {
                        JSONObject obj = j.getJSONObject(i);
                        String name=obj.getString("CatName");
                        CatID.put(name,obj.getString("CID"));
                        Cat.add(name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,Cat);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                catSpine.setAdapter(adapter);
            }
        },"get/GetCategory");
        catSpine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    catID=CatID.get(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishApeal(new CallBackobj() {
                    @Override
                    public void onResponse(JSONObject j) {
                        openApeal(j);
                    }
                });
            }
        });
    }
    public void publishApeal(final CallBackobj succ){
        JSONObject jsonParams= new JSONObject();
        try {
            jsonParams.put("uid", SessionInfo.getId());
            jsonParams.put("catID",catID);
            jsonParams.put("Decription",editText.getText());
            jsonParams.put("content",brief.getText());
            jsonParams.put("title",title.getText());
            HttpClient.post(this, "post/newِApeal", jsonParams, new CallBackPost() {

                @Override
                public void onResponse(byte[] responseBody) {
                    try {
                        String y=new String(responseBody, "UTF-8");
                        JSONObject js =new JSONObject(y);
                        openApeal(js);
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
    public void openApeal(JSONObject msj){
        try {
            String y =msj.getString("Massage");
            Toast.makeText(getApplicationContext(),y, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent =new Intent(this,ApealView.class);
        startActivity(intent);
    }


}
