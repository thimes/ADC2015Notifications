package com.thimes.notifications;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class DeleteIntentService extends IntentService {

    public DeleteIntentService() {
        super("DeleteIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.v("TRAVIS", "The notification was deleted!!");
        }
    }
}
