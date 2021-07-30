package com.example.podsstore;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class BioFuelFirebaseService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();


    @Override
    public void onNewToken(String s) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w( "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.e("My Token",token);
                    }
                });


    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);




        int type=getSharedPreferences("login_info",MODE_PRIVATE).getInt("usertype",-1);

        Map<String, String> data = remoteMessage.getData();
        String body = data.get("body");
        String title = data.get("title");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 101, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            AudioAttributes att = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            channel = new NotificationChannel("222", "my_channel", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(channel);
        }

        if (remoteMessage.getNotification().getImageUrl() != null){

            Uri imageUrl = remoteMessage.getNotification().getImageUrl();


            Log.d("imageUrl: ", ""+imageUrl);




            if (imageUrl != null) {


                try {
                    URL url = new URL(remoteMessage.getNotification().getImageUrl()+"");
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    Log.d("imageUrl: ", ""+bitmap);

                    NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
                    bigPictureStyle.setBigContentTitle(title);
                    bigPictureStyle.bigPicture(bitmap);
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(
                                    getApplicationContext(), "222")
                                    .setContentTitle(remoteMessage.getNotification().getTitle())
                                    .setAutoCancel(true)
                                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.bluelogo))
                                    .setSmallIcon(R.drawable.bluelogo)
                                    .setStyle(bigPictureStyle)
                                    .setContentText("")
                                    .setContentIntent(pi)
                            ;


                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);

                    nm.notify(123, builder.build());


                } catch(IOException e) {
                    System.out.println(e);
                }


            }


        }




    }
}