package tiengduc123.com.q1000videosB1deutschlernen.QFile;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;

import tiengduc123.com.q1000videosB1deutschlernen.MainActivity;
import tiengduc123.com.q1000videosB1deutschlernen.R;

/**
 * Created by Dell on 09/04/2016.
 */
public class qDevice {
    View v;
    private int notificationId = 1;
    private NotificationManager notificationManager;
    String title;
    String text;

    public void qDevice(View v, String text, String title) {
        this.v = v;
        this.text = text;
        this.title = title;
    }

    public void showNotify() {

    }
}
