package com.example.mohamed.sochub;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.sochub.Comman.AddToEmind;
import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Entitis.CallBackjson;
import com.example.mohamed.sochub.Entitis.SessionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Apeals extends Fragment {


    // TODO: Rename and change types of parameters
    private String CID;
    private String CName;
    Context context;
    JSONArray Mtimeline;
    ListView ContentView;
    ProgressBar load;
    private int mode;

    public void setMode(int x) {
        mode = x;
    }

    public Apeals() {
        // Required empty public constructor
    }


        public void setc_id_name(String id,String name){
            CID=id;
            CName=name;
        }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void reset(){
    load.setVisibility(View.VISIBLE);
        ContentView.setVisibility(View.GONE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        View view=inflater.inflate(R.layout.fragment_apeals, container, false);
        ContentView=(ListView)view.findViewById(R.id.ApealBtief);
        load=(ProgressBar)view.findViewById(R.id.progressBar3);
        load.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        return view;
    }
    public void updateList(){
    String apiPath = mode==0 ? "CategoryFeed/GetPostByCategory?CatID="+CID : "Reminding/Mylist?sid="+ SessionInfo.getSid();
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                Mtimeline=j;
                CustomAdaptor Custom=new CustomAdaptor();
                ContentView.setAdapter(Custom);
                load.setVisibility(View.GONE);
                ContentView.setVisibility(View.VISIBLE);
            }
        },apiPath);

    }
    // TODO: Rename method, update argument and hook method into UI event

    public class CustomAdaptor extends BaseAdapter {

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
                JSONObject n = Mtimeline.getJSONObject(position);
                convertView = getLayoutInflater().inflate(R.layout.napeal_view, null);
                TextView name = (TextView) convertView.findViewById(R.id.by);
                TextView apeal = (TextView) convertView.findViewById(R.id.Content);
                TextView date = (TextView) convertView.findViewById(R.id.Date);
                TextView title = (TextView) convertView.findViewById(R.id.Title);
                ImageButton fav = (ImageButton)convertView.findViewById(R.id.fav);
                RadioButton rad=(RadioButton)convertView.findViewById(R.id.Closed);
                if(mode ==0)
                    rad.setVisibility(View.GONE);
                if (mode==1){
                    final boolean closed =n.getBoolean("closed");
                    rad.setChecked(closed);
                }
                //  TextView UID=(TextView)convertView.findViewById(R.id.textId);
                //  RadioButton Closed=(RadioButton)convertView.findViewById(R.id.Closed);
               // ApealView.Aid.add(n.getString("AID"));
                final String aid=n.getString("AID");
                final String Name=n.getString("name");
                final String Date=n.getString("date");
                final String Title=n.getString("Title");
                name.setText(Name);
                apeal.setText(n.getString("Content") + "...See more");
                date.setText(Date);
                title.setText(Title);
                // UID.setText(n.getString("ID"));
                // Closed.setChecked(n.getBoolean("closed"));

                apeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainPage)getActivity()).setPublisher_title_Date(Name,Title,Date,aid);
                        ((MainPage)getActivity()).setFragment(2);

                    }
                });
                fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   if (mode==0)
                        AddToEmind.Remind(context,aid);
                   else
                       Toast.makeText(context,"not implemnted yet",Toast.LENGTH_SHORT).show();
                    }
                });


                return convertView;
            } catch (JSONException e) {
                e.printStackTrace();

            }
            return null;

        }


    }
}