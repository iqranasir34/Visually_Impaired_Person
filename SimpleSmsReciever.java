package com.example.smsrecieved;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Locale;

public class SimpleSmsReciever extends BroadcastReceiver {
TextToSpeech t1;
    String toSpeak;
    private static final String TAG = "Message recieved";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        final SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[0]);

        // Start Application's  MainActivty activity

        Intent smsIntent=new Intent(context,MainActivity.class);

        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());

        smsIntent.putExtra("Message", messages.getMessageBody());
        context.startActivity(smsIntent);

        // Get the Sender Message : messages.getMessageBody()
        // Get the SenderNumber : messages.getOriginatingAddress()


        t1 = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                    t1.setLanguage(Locale.UK);


                    Toast.makeText(context, "SMS Received From :"+messages.getOriginatingAddress()+"\n"+ messages.getMessageBody(), Toast.LENGTH_LONG).show();
                    String messagespeak="SMS Received From :"+messages.getOriginatingAddress()+"\n"+ messages.getMessageBody();
                    t1.speak(messagespeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}
