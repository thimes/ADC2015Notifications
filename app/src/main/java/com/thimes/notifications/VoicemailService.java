package com.thimes.notifications;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by thimes on 7/29/15.
 */
public class VoicemailService extends IntentService {

    public VoicemailService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this, "Voicemail will be provided", Toast.LENGTH_LONG).show();
    }
}
