package com.wissen.esds.service.impl;

import com.wissen.esds.dao.SenderDao;
import com.wissen.esds.dao.impl.FirebaseCloudMessageSenderDao;
import com.wissen.esds.service.SenderService;

public class FirebaseCloudMessageSenderService implements SenderService {

    SenderDao firebaseCloudMessageSender;

    @Override
    public void send(String receiver, String message) {
        firebaseCloudMessageSender = new FirebaseCloudMessageSenderDao();
        firebaseCloudMessageSender.send(receiver, message);
    }

}
