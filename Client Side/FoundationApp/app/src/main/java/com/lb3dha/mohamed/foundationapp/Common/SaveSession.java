package com.lb3dha.mohamed.foundationapp.Common;

import android.content.Context;

import com.lb3dha.mohamed.foundationapp.Entitis.SessionInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveSession {
    private static final String filePath ="lb3dhacredintial.txt" ;
    public static void saveCreadintiol(String SID, String UID, String NAME, Context x){
        try{
            FileOutputStream mOutput= x.openFileOutput(filePath,x.MODE_PRIVATE);
            String Data =SID+" "+UID+" "+NAME+" ";
            mOutput.write(Data.getBytes());
            mOutput.flush();
            mOutput.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }

        public static int readCreadintiol (Context x){
        try {
            FileInputStream mInput= x.openFileInput(filePath);
            byte [] data=new byte[256];
            mInput.read(data);
            mInput.close();
            String d= new String (data);
            String [] input =d.split(" ");
            SessionInfo.setSid(input[0]);
            SessionInfo.setId(input[1]);
            SessionInfo.setName(input[2]);
            return 1;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return -1;
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        }
        public static void deletefile(Context x){
            x.deleteFile(filePath);
        }
    }
