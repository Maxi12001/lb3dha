package com.example.mohamed.sochub.Entitis;

public class SessionInfo {
    private static String Sid,name,id;
    public static void setSid(String SID){
        Sid=SID;
    }

    public static void setId(String Id) {
        id = Id;
    }
    public static void setName(String Name){
        name=Name;
    }

    public static String getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getSid() {
        return Sid;
    }
}