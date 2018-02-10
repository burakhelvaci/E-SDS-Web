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

        String requestBody = prepareJSON(receiver, message);

        try {
            String response = Jsoup.connect(url).timeout(1000).ignoreContentType(true).header("Content-Type", "application/json").header("Authorization", "key=" + key).requestBody(requestBody).post().text();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(FirebaseCloudMessageSenderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String prepareJSON(String receiver, String message) {
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("message", message);

        JSONObject notificationJSON = new JSONObject();
        notificationJSON.put("title", "Yeni Mesaj");
        if (message.length() > 50) {
            notificationJSON.put("body", message.substring(0, 50) + "...");
        } else {
            notificationJSON.put("body", message);
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("to", receiver);
        requestBody.put("data", messageJSON);
        requestBody.put("notification", notificationJSON);

        return requestBody.toString();
    }
}
