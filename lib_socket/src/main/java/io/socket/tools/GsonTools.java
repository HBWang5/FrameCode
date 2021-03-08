package io.socket.tools;

import com.google.gson.Gson;

public class GsonTools {
    public static Gson gson;
    private GsonTools(){}
    public static synchronized Gson getInstance(){
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
