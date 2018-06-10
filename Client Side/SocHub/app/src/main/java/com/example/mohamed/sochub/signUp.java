package com.example.mohamed.sochub;


import android.app.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.mohamed.sochub.Comman.HttpClient;
import com.example.mohamed.sochub.Entitis.CallBackPost;
import com.example.mohamed.sochub.Entitis.Callback;
import com.example.mohamed.sochub.Entitis.SessionInfo;
import com.example.mohamed.sochub.Validation.EmailValidator;

import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class signUp extends AppCompatActivity {
    private Button _Register;
    String bt,city,state;
    final Calendar myCalendar = Calendar.getInstance();
    private Spinner bloodType;
    private EditText _name,_mail,_pass,_repass,_state,_city,_bdate,_phoneNumber;

    //private AsyncHttpClient client =new AsyncHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        _name=(EditText)findViewById(R.id.name);
        _mail=(EditText)findViewById(R.id.email);
        _pass=(EditText)findViewById(R.id.pass);
        _repass=(EditText)findViewById(R.id.repass);
        _state=(EditText)findViewById(R.id.st);
        _city=(EditText)findViewById(R.id.ci);
        _bdate=(EditText)findViewById(R.id.bida);
        _Register=(Button)findViewById(R.id.signUp);
        _phoneNumber=(EditText)findViewById(R.id.ph);
        _Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });
        bloodType=(Spinner)findViewById(R.id.blood);
        String[] bloodT=new String[]{
            "UN","A+","A-","B+","B-","AB+","AB-","O+","O-"
        };
        state="cairo";
        city="Mokattm";
        ArrayAdapter<String>SpinnAddapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bloodT);
        SpinnAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodType.setAdapter(SpinnAddapter);
        bloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bt =parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    bt=parent.getItemAtPosition(0).toString();
            }
        });
      final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        _bdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(signUp.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        _bdate.setText(sdf.format(myCalendar.getTime()));
    }
    public void Masage(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();}

        private void signUpClicked()
    {
        String Email= _mail.getText().toString();
        String Pass= _pass.getText().toString();
        String conPass= _repass.getText().toString();
        String date=_bdate.getText().toString();
        String cit= _city.getText().toString();
        String sta= _state.getText().toString();
        String name= _name.getText().toString();
        String Phone=_phoneNumber.getText().toString();
        if (!EmailValidator.emailValidator(Email)){
            Masage("Invalid email address");
            return;}
         if(!Pass.equals(conPass)){
            Masage("the password not match");
            return;
         }
         if(name.equals(""))
         {
             Masage("name required");
             return;
         }
         if(bt.equals(""))
             bt="UN";
        if (date.equals(""))
            date="2000-1-1";
        if(!cit.equals(""))
            city=cit;
        if (!sta.equals(""))
            state=sta;

        JSONObject jsParam=new JSONObject();
        try {
            jsParam.put("name",name);
            jsParam.put("regMail",Email);
            jsParam.put("pass",Pass);
            jsParam.put("Bdate",date);
            jsParam.put("City",cit);
            jsParam.put("state",sta);
            jsParam.put("phoneno",Phone);
            jsParam.put("blood",bt);
            HttpClient.post(this, "Signing/NewUser", jsParam, new CallBackPost() {
                @Override
                public void onResponse(byte[] responseBody) {
                    try {
                        String Y = new String(responseBody, "UTF-8");
                        JSONObject jsonobj = new JSONObject(Y);
                        String msg = jsonobj.getString("Massage");
                        if(!msg.equals("Badinput")){
                            Masage(msg);
                            opencomp();
                           /* String Sid = jsonArr.get("SID").toString();
                            String name=jsonArr.get("name").toString();
                            String id=jsonArr.get("ID").toString();
                            SessionInfo.setSid(Sid);
                            SessionInfo.setId(id);
                            SessionInfo.setName(name);
                            openApeal();*/}
                        else{
                            Masage(Y);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Callback<Integer>() {
                @Override
                public void onresponse() { }});
        }catch (JSONException je){
            je.printStackTrace();
        }
    }
    public void opencomp(){
        Intent intent =new Intent(this,compSignUp.class);
        startActivity(intent);
    }

}
