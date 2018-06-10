package com.example.mohamed.sochub.Comman;

import android.content.Context;

import com.example.mohamed.sochub.Entitis.SessionInfo;

import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;

public class SaveSession {
    private static final String filePath ="lb3dhacredintial.txt" ;
    private static int size;
    public static void saveCreadintiol(String SID, String UID, String NAME, Context x){
        try{
            FileOutputStream mOutput= x.openFileOutput(filePath,x.MODE_PRIVATE);
            String Data =SID+" "+UID+" "+NAME+" ";
            byte [] g= Data.getBytes();
            size=g.length;
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
            byte [] data=new byte[128];
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
