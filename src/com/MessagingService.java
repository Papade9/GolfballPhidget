package com;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.LocalDateTime;

public class MessagingService {
    private static transient MessagingService _instance;
    private final String USERNAME = Settings.getSetting("Messaging_Service_Username");
    private final String PASSWORD = Settings.getSetting("Messaging_Service_Password");
    private final String FROM_PHONE_NUMBER = Settings.getSetting("Messaging_Service_From_Phone");

    public static MessagingService getInstance(){
        if(_instance == null)
            _instance = new MessagingService();
        return _instance;
    }

    private MessagingService(){
        Twilio.init(USERNAME,PASSWORD);
    }

    public boolean sendSMS(String message, String toPhoneNumber, boolean waitForconfirm){
        boolean response = !waitForconfirm;
        try {
            Message msg = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(FROM_PHONE_NUMBER), message).create();
            if (waitForconfirm) {
                Message confirm;
                int tries = 0;
                do {
                    confirm = Message.fetcher(msg.getSid()).fetch();
                    response = confirm.getStatus().equals(Message.Status.DELIVERED);
                } while (tries < 10 && (!response));
            }
        }catch(ApiException ex){
            MainScreen.getInstance().addLogEntry("Unable to send text message: " + ex.getMoreInfo());
            response = false;
        }
        return response;
    }
}
