package com;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MessagingService {
    private static transient MessagingService _instance;
    private final String USERNAME = Settings.getSetting("Messaging_Service_Username");
    private final String PASSWORD = Settings.getSetting("Messaging_Service_Password");
    private final String FROM_PHONE_NUMBER = Settings.getSetting("Messaging_Service_From_Phone");
    private final String PUTTS_PORTAL_KEY = Settings.getSetting("Putts_Portal_Key");
    private boolean _twilioInitiated = false;

    public static MessagingService getInstance(){
        if(_instance == null)
            _instance = new MessagingService();
        return _instance;
    }

    private MessagingService(){}

    public boolean sendPuttsNotification(String title, String message, boolean mod, boolean task, String[] pcodes){
        if(pcodes == null && !mod)
            return false;
        JwtBuilder jwtBuilder = Jwts.builder().setPayload("J").signWith(SignatureAlgorithm.HS256, PUTTS_PORTAL_KEY.getBytes());
        String compactKey = jwtBuilder.compact();
        JsonObject obj = new JsonObject();
        obj.addProperty("mods",(mod ? "1" : "0"));
        obj.addProperty("locationId",Register.get().getLocation().getPuttsLocationId());
        obj.addProperty("task",(task ? "1" : "0"));
        if(pcodes != null) {
            JsonArray array = new JsonArray();
            for(String s : pcodes)
                array.add(new JsonPrimitive(s));
            obj.add("pcodes", array);
        }
        obj.addProperty("notificationMessage",message);
        obj.addProperty("title",title);
        HttpResponse.RequestBuilder builder = new HttpResponse.RequestBuilder(Settings.getSetting("Putts_Portal_Server") +"/notifications/notify");
        builder.setJsonStringContent(obj);
        builder.addHeader("Authorization","Bearer " + compactKey);
        HttpResponse response = builder.POST();
        return response.status == 200 && response.response.has("status") && response.response.get("status").getAsString().equals("successful");
    }

    public boolean sendSMS(String message, String toPhoneNumber, boolean waitForconfirm){
        boolean response = !waitForconfirm;
        if(!_twilioInitiated){
            Twilio.init(USERNAME,PASSWORD);
            _twilioInitiated = true;
        }
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
