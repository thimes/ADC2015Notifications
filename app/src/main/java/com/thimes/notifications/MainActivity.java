package com.thimes.notifications;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private int mId = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showNotification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_notify_sdcard)
                        .setContentTitle("New data pack available")
                        .setContentText("Old Hollywood is available for purchase!")
                        .setContentIntent(getContentPendingIntent())
                        .addAction(android.R.drawable.stat_notify_sync, "Get it!", getSyncPendingIntent())
                        .setAutoCancel(true);
        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(this);

        mNotificationManager.notify(mId, mBuilder.build());
    }

    private PendingIntent getSyncPendingIntent() {
        return PendingIntent.getActivity(this, 1, new Intent(this, SyncActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private PendingIntent getContentPendingIntent() {
        Intent followIntent = new Intent(this, SecondaryActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(followIntent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

    }
}
