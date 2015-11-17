package com.thimes.notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;


public class MainActivity extends AppCompatActivity {

    private int mId = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        Bitmap mySmallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_2);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_stat_3)
               .setContentTitle("New big icon")
               .setContentText("check it out!")
               .setLargeIcon(mySmallBitmap);
        mNotificationManager.notify(mId, builder.build());
    }

    @SuppressLint("NewApi") // only suppressing it because I know I've checked for it
    private Notification buildCustomNotification() {
        Notification n = new Notification();

        n.icon = R.drawable.ic_stat_5;
        n.when = System.currentTimeMillis();
//        n.defaults = Notification.DEFAULT_ALL;
        n.tickerText = "New custom notification has arrived!";

        Bitmap myBigBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.custom_image);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.rich_notification);

        contentView.setImageViewBitmap(R.id.imageL, myBigBitmap);
        contentView.setImageViewBitmap(R.id.imageR, myBigBitmap);
        contentView.setTextViewText(R.id.title, "Hi Notification!");
        contentView.setTextViewText(R.id.text, "Not so bad...right?");

        n.contentView = contentView;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            RemoteViews expandedContentView = buildExpandedRemoteViews();
            n.bigContentView = expandedContentView;
        }

        return n;
    }

    private RemoteViews buildExpandedRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.rich_notification_expanded);

        remoteViews.setImageViewResource(R.id.big_cat, R.drawable.big_image);
        remoteViews.setImageViewResource(R.id.cat_tl, R.drawable.cat1);
        remoteViews.setImageViewResource(R.id.cat_tm, R.drawable.cat2);
        remoteViews.setImageViewResource(R.id.cat_tr, R.drawable.cat3);
        remoteViews.setImageViewResource(R.id.cat_ml, R.drawable.cat4);
        remoteViews.setImageViewResource(R.id.cat_mr, R.drawable.cat5);
        remoteViews.setImageViewResource(R.id.cat_bl, R.drawable.cat6);
        remoteViews.setImageViewResource(R.id.cat_bm, R.drawable.cat7);
        remoteViews.setImageViewResource(R.id.cat_br, R.drawable.cat8);

        return remoteViews;
    }

}
