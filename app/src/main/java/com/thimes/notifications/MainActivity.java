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
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_notify_sync)
                        .setContentTitle("Download")
                        .setProgress(0, 0, true)
                        .setContentText("Starting download...");
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                        }

                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr += 5) {
                            // Sleeps the thread, simulating an operation that takes time
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                            }

                            // Sets the progress indicator to a max value, the current completion percentage, and "determinate" state
                            mBuilder.setProgress(100, incr, false).setContentText("Progress: " + incr + "%");
                            // Displays the progress bar for the first time.
                            mNotificationManager.notify(mId, mBuilder.build());
                        }

                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0, 0, false);
                        mNotificationManager.notify(mId, mBuilder.build());
                    }
                }
        ).start(); // Starts the thread by calling the run() method in its Runnable
    }
}
