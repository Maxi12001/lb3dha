package com.example.mohamed.sochub;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Entitis.CallBackjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.graphics.Color.RED;

public class profile extends AppCompatActivity {
    public TextView Case,BloodType,TrustedBy,trustedIn,name;
    JSONArray ProfileApeal;
    ListView apealList;
    ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uid=getIntent().getStringExtra("UID");
        setContentView(R.layout.activity_profile);
        Case = (TextView) findViewById(R.id.Apealno);
        BloodType = (TextView) findViewById(R.id.BloodTex);
        TrustedBy = (TextView) findViewById(R.id.TrustBy);
        trustedIn = (TextView) findViewById(R.id.TrustIn);
        name = (TextView) findViewById(R.id.name);
        apealList = (ListView) findViewById(R.id.listview);
        load=(ProgressBar)findViewById(R.id.progressBar);
        load.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                try {
                    JSONObject i = j.getJSONObject(0);
                    name.setText(i.getString("name"));
                    BloodType.setText(i.getString("bloodtype"));
                    Case.setText(i.getString("noOfapeal"));
                    trustedIn.setText(i.getString("trustedin"));
                    TrustedBy.setText(i.getString("trustedBy"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, "profile/profileInfo?uid=" + uid);

        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                ProfileApeal=j;
                profile.CustomAdaptor customAdaptor=new profile.CustomAdaptor();
                apealList.setAdapter(customAdaptor);
                load.setVisibility(View.GONE);
                apealList.setVisibility(View.VISIBLE);
            }
        },"profile/GetUserApeal?uid="+uid);
    }
    public class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return ProfileApeal.length();
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
                JSONObject n=ProfileApeal.getJSONObject(position);
                convertView=getLayoutInflater().inflate(R.layout.napeal_view,null);
                TextView name=(TextView)convertView.findViewById(R.id.by);
                TextView apeal=(TextView)convertView.findViewById(R.id.Content);
                TextView date=(TextView)convertView.findViewById(R.id.Date);
                TextView title=(TextView)convertView.findViewById(R.id.Title);
                RadioButton rad=(RadioButton)convertView.findViewById(R.id.Closed);
                    rad.setVisibility(View.GONE);
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
