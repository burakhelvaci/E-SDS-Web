package com.wissen.esds.dao.impl;

import com.wissen.esds.dao.SenderDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class FirebaseCloudMessageSenderDao implements SenderDao {

    @Override
    public void send(String receiver, String message) {
        String url = "https://fcm.googleapis.com/fcm/send";
        String key = "AAAA4OYn4_0:APA91bFUs-j6WYyad6iD3CmbP1eNKuyL5fMH8I1-TAB2y3dre5e-vlZR633sjIH0y8libR_o5i6p-iFBddehDmzTaSViAe19Y2n_dFf0tWnEeOVqeODyCMcOWJ_cmmgHq6TukWBvtQKN";

        JSONObject requestBody = new JSONObject();
        requestBody.put("to", "/topics/" + receiver);
        requestBody.put("data", new JSONObject().put("message", message));
        
        try {
            Jsoup.connect(url).timeout(1000).ignoreContentType(true).header("Content-Type", "application/json").header("Authorization", "key=" + key).requestBody(requestBody.toString()).post().text();
        } catch (IOException ex) {
            Logger.getLogger(FirebaseCloudMessageSenderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
