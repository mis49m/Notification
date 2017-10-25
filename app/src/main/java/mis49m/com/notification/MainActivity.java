package mis49m.com.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final int NOTIFICATION_ID = 1;
    private final String KEY = "message";

    Button btnCreate, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYes = new Intent(getBaseContext(), ResultActivity.class);
                intentYes.putExtra(KEY, "yes");
                PendingIntent pendingIntentYes = PendingIntent.getActivity(MainActivity.this, 1,
                        intentYes, PendingIntent.FLAG_CANCEL_CURRENT);

                Intent intentNo = new Intent(getBaseContext(), ResultActivity.class);
                intentNo.putExtra(KEY, "no");
                PendingIntent pendingIntentNo = PendingIntent.getActivity(MainActivity.this, 2,
                        intentNo, PendingIntent.FLAG_CANCEL_CURRENT);

                Intent resultIntent = new Intent(getBaseContext(), ResultActivity.class);
                resultIntent.putExtra(KEY, "message");
                PendingIntent resultPendingIntent = PendingIntent.getActivity(getBaseContext(), 3,
                        resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext());
                mBuilder.setSmallIcon(R.drawable.ic_info_black_24dp);
                mBuilder.setContentTitle("My notification");
                mBuilder.setContentText("Do you want to cancel operation?");
                mBuilder.setAutoCancel(true);
                mBuilder.addAction(R.drawable.yes, "Yes", pendingIntentYes);
                mBuilder.addAction(R.drawable.no, "No", pendingIntentNo);
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationManager mNotificationManager = (NotificationManager)
                        getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager mNotificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.cancel(NOTIFICATION_ID);
            }
        });
    }
}
