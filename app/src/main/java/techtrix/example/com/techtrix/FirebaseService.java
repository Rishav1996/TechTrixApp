package techtrix.example.com.techtrix;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseService extends Service {
    public FirebaseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase fd=FirebaseDatabase.getInstance();
        DatabaseReference df=fd.getReference("TechInfo");
        ValueEventListener val=new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> db=dataSnapshot.getChildren();
                for(DataSnapshot dbval:db)
                {
                    int id=Integer.parseInt(dbval.child("id").getValue().toString());
                    String mess =dbval.child("message").getValue().toString();
                    String event=dbval.child("event").getValue().toString();
                    DatabaseHandler databaseHandler=new DatabaseHandler(getApplicationContext());
                    NotifyMessage nm=new NotifyMessage();
                    nm.setId(id);
                    nm.setEventName(event);
                    nm.setMessDesc(mess);
                    if(databaseHandler.addMessage(nm))
                    {
                        if(mess.contains("404")) {
                            databaseHandler.delete();
                        }
                        else
                        {
                            note(mess, event);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addValueEventListener(val);

    }
    public void note(String mess, String event) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.drawable.techlogo).setContentTitle(event).setContentText(mess);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
