package com.example.mohamed.sochub;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Entitis.CallBackjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatFrag extends Fragment {
private ListView CategoryList;
public JSONArray Category;
public MainPage xPage;
ProgressBar load;
    public CatFrag() {
        // Required empty public constructor
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

     View view= inflater.inflate(R.layout.fragment_cat, container, false);
     CategoryList=(ListView)view.findViewById(R.id.Category);
        xPage=(MainPage)getActivity();
        load=(ProgressBar)view.findViewById(R.id.progressBar2);
        load.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                Category=j;
                CatAdaptor myAdaptor=new CatAdaptor();
                CategoryList.setAdapter(myAdaptor);
                load.setVisibility(View.GONE);
                CategoryList.setVisibility(View.VISIBLE);
            }
        },"CategoryFeed/GetCategory");

    return view;
    }

    public class CatAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return Category.length();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                JSONObject n=Category.getJSONObject(position);
                convertView=getLayoutInflater().inflate(R.layout.cat_card,null);
                final TextView catName=(TextView)convertView.findViewById(R.id.CatName);
                final TextView catId=(TextView)convertView.findViewById(R.id.CatId);
                final TextView catdsc=(TextView)convertView.findViewById(R.id.dsc);
                TextView noOfApeals=(TextView)convertView.findViewById(R.id.nofApeal);
                TextView more=(TextView)convertView.findViewById(R.id.More);
                final String catid,catname,no;
                catid=n.getString("CID");
                catname=n.getString("CatName");
                no=n.getString("Count");
                catName.setText(catname);
                catId.setText(catid);
                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainPage)getActivity()).setC_ID_name(catId.getText().toString(),catName.getText().toString());
                        ((MainPage)getActivity()).setFragment(1);
                    }
                });
                catId.setText(catid);
                catdsc.setText(n.getString("dscript"));
                noOfApeals.setText("No of Apeal: "+no);
                return convertView;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
