package com.example.mohamed.sochub;


import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mohamed.sochub.Comman.HttpClient;

import android.support.v4.view.GravityCompat;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import android.content.*;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Button;
import com.example.mohamed.sochub.Entitis.*;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
//import com.example.mohamed.sochub.Comman.HttpClient;
public class ApealView extends AppCompatActivity {
    private Button nApeal;
    public JSONArray Mtimeline;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    public static ArrayList<String> Aid=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apeal_view);
        Toolbar toolbar=(Toolbar)findViewById(R.id.apealBar);
        setSupportActionBar(toolbar);
       ActionBar actionbar=getSupportActionBar();
       actionbar.setTitle("يسر");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_list_black_24dp);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        nApeal=(Button)findViewById(R.id.NewApeal);
        nApeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNActivity(1);
            }
        });
        final ListView listView=(ListView)findViewById(R.id.Apeals);
        String x=SessionInfo.getSid();
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                Mtimeline=j;
                CustomAdaptor customAdaptor=new CustomAdaptor();
                listView.setAdapter(customAdaptor);
            }
        },"get/newsfeed?sid="+SessionInfo.getSid());
        navView=(NavigationView)findViewById(R.id.nav_view);
        navListner();
    }

    public void openNActivity(int ActivityNo){
        Intent intent ;
        if (ActivityNo==1)
            intent =new Intent(this,newApeal2.class);
        else
            intent=new Intent(this,profile.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int X =item.getItemId();

        if(item.getItemId()==R.id.Rlist){
            openNActivity(2);
            return true;
        }
        else if(X==android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Not implemnted Yet", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);}


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu,menu);
        return true;
    }

    public void navListner(){
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                item.setChecked(false);
                int id =item.getItemId();
                switch (id){
                    case R.id.RemindList:
                        openNActivity(2);
                        break;
                    case R.id.Myprofile:

                        OpenProfileID.setProfileID(Integer.parseInt(SessionInfo.getId()));
                        openNActivity(3);
                        break;
                     default: Toast.makeText(getApplicationContext(),"Not implemnted Yet", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    public class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return Mtimeline.length();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            try {
                JSONObject n=Mtimeline.getJSONObject(position);
                convertView=getLayoutInflater().inflate(R.layout.napeal_view,null);
                TextView name=(TextView)convertView.findViewById(R.id.by);
                TextView apeal=(TextView)convertView.findViewById(R.id.Content);
                TextView date=(TextView)convertView.findViewById(R.id.Date);
                TextView title=(TextView)convertView.findViewById(R.id.Title);
              //  TextView UID=(TextView)convertView.findViewById(R.id.textId);
              //  RadioButton Closed=(RadioButton)convertView.findViewById(R.id.Closed);

                ApealView.Aid.add(n.getString("AID"));
                name.setText( n.getString("name"));
                apeal.setText(n.getString("Content"));
               date.setText(n.getString("date"));
                title.setText(n.getString("Title"));
               // UID.setText(n.getString("ID"));
               // Closed.setChecked(n.getBoolean("closed"));

                return convertView;
            } catch (JSONException e) {
                e.printStackTrace();

            }
            return null;

        }


    }

}
