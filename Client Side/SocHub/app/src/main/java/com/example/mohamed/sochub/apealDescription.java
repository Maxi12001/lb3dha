package com.example.mohamed.sochub;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Entitis.CallBackjson;
import com.example.mohamed.sochub.Comman.AddToEmind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class apealDescription extends Fragment {
    public apealDescription(){}
    ProgressBar load;
    private String name,title,aid,date,UID;
    ImageButton Fav,Con,mass;
    Context context;
    public void setPar(String Name,String Title,String Aid,String Date){
        name=Name;
        title=Title;
        aid=Aid;
        date=Date;
    }
    private TextView Title,By,Date,Dsc;
    private Toolbar actionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        View view=inflater.inflate(R.layout.fragment_apeal_description, container, false);
        Title=(TextView)view.findViewById(R.id.Title);
        By=(TextView)view.findViewById(R.id.By);
        Date=(TextView)view.findViewById(R.id.Date);
        Dsc=(TextView)view.findViewById(R.id.dsc);
        Fav=(ImageButton) view.findViewById(R.id.fav);
        mass=(ImageButton)view.findViewById(R.id.msg);
        Con=(ImageButton)view.findViewById(R.id.con);
        load=(ProgressBar)view.findViewById(R.id.progressBar64);
        load.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Remind();
            }
        });
        mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not implemted Yet", Toast.LENGTH_SHORT).show();
            }
        });
        Con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not implemted Yet", Toast.LENGTH_SHORT).show();
            }
        });
        By.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openProfile();
            }
        });
        return view;
    }
    public void UpdateView(){
        Title.setText(title);
        By.setText(name);
        Date.setText(date);
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                try {
                    JSONObject i=j.getJSONObject(0);
                    String x=i.getString("Description");
                    String y=i.getString("UID");
                    load.setVisibility(View.GONE);
                    setDsc(x,y);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },"Feed/GetDsc?AID="+aid);
    }

    public void setDsc(String Text,String Uid) {
        Dsc.setText(Text);
        Dsc.setVisibility(View.VISIBLE);
        UID=Uid;
    }
    private void Remind(){
        AddToEmind.Remind(context,aid);

    }
    private void openProfile(){
        Intent  intent=new Intent(context,profile.class);
        intent.putExtra("UID",UID);
        startActivity(intent);
    }
    public void reset(){
        load.setVisibility(View.VISIBLE);
        Dsc.setVisibility(View.GONE);
    }


}
