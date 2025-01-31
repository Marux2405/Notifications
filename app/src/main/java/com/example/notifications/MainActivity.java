package com.example.notifications;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        createNotificationChanel();
        button.setOnClickListener(v -> {
            sendNotification();
        });
        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener(v -> {
            sendNotificationLong();
        });

        Button button2 = findViewById(R.id.button3);
        button2.setOnClickListener(v->{
            sendNotificationLargeIcon();
        });


    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // O nie 0, sprawdza czy jest aktualna wersja, po postawieniu kropki pokazuje wesje, sdkint wersja naszego projektu
            CharSequence name = "kanał powiadomień"; // nazwa kanału
            String description = "Opis kanału"; // opis kanału
            int importance = NotificationManager.IMPORTANCE_DEFAULT; //wazność kanalu(mozna wstawic zamiast Notificationmanager... samą wartość
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance); // tworzenie powiadomienie 1 string 2 nazwa 3 waznosc
            channel.setDescription(description);// dodawanie opisu do powiadomienia
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    private void sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Nowe Powiadomienie 3tp").setContentText("Nowy tekst dla 3tp").setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    private void sendNotificationLong() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Nowe Powiadomienie 3tp").setStyle(new NotificationCompat.BigTextStyle().bigText("Aenean lectus lectus, molestie vel tincidunt a, commodo cursus diam. Praesent rhoncus finibus urna ut lobortis. Vestibulum orci arcu, ultricies in justo id, mollis imperdiet dui. Aliquam sagittis, nulla vitae maximus sodales, lectus ligula egestas felis, vel commodo ipsum magna sed magna. Ut et gravida elit. In et bibendum enim. Nullam non odio viverra, efficitur mauris ac, maximus nisi.")).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }
    private void sendNotificationLargeIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.obraz);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Nowe Powiadomienie 3tp").setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }
}