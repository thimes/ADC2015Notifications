package com.thimes.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
        findViewById(R.id.hello).postDelayed(new Runnable() {
            @Override
            public void run() {
                buildNotification("Public", NotificationCompat.VISIBILITY_PUBLIC);
                buildNotification("Private", NotificationCompat.VISIBILITY_PRIVATE);
                buildNotification("Secret", NotificationCompat.VISIBILITY_SECRET);
            }
        }, 10000);
    }

    private void buildNotification(String title, int visibility) {
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_notify_sync) // 36dp max
                        .setContentTitle(title)
                        .setContentText("Content Text")
                        .setVisibility(visibility)
                ;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(visibility + 4, mBuilder.build());
    }
}
