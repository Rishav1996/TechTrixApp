package techtrix.example.com.techtrix;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static int a = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this,FirebaseService.class));
        if (!callRequestAllowed()) {
            askForCallPermission();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        switchLayout(1);
        eventPage();
        Firebase();
        //DatabaseHandler databaseHandler=new DatabaseHandler(getApplicationContext());
        //databaseHandler.delete();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (item.getTitle().toString().compareTo("Events") == 0) {
            switchLayout(1);
        }
        if (item.getTitle().toString().compareTo("Notifications") == 0) {
            switchLayout(2);
        }
        if (item.getTitle().toString().compareTo("About") == 0) {
            switchLayout(3);
        }
        if (item.getTitle().toString().compareTo("Contact") == 0) {
            switchLayout(4);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void switchLayout(int a)
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        View v1 = (View) findViewById(R.id.event);
        View v2 = (View) findViewById(R.id.notify);
        View v3 = (View) findViewById(R.id.about);
        View v4 = (View) findViewById(R.id.contact);
        v1.setVisibility(View.INVISIBLE);
        v2.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        v4.setVisibility(View.INVISIBLE);
        switch (a) {
            case 1:
                v1.setVisibility(View.VISIBLE);
                break;
            case 2:
                v2.setVisibility(View.VISIBLE);
                break;
            case 3:
                v3.setVisibility(View.VISIBLE);
                break;
            case 4:
                v4.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void note(String mess, String event) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.drawable.techlogo).setContentTitle(event).setContentText(mess);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(null);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
    public void previousNotification(String mess, String event) {
        LinearLayout mainll = (LinearLayout) findViewById(R.id.main_notify);
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        llp.setMargins(0, 0, 0, 1);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(llp);
        ll.setBackgroundColor(Color.parseColor("#dcb374"));
        ll.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams llt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView head = new TextView(this);
        head.setText(event);
        head.setTextSize(18f);
        head.setLayoutParams(llt);
        ll.addView(head);
        TextView text = new TextView(this);
        text.setText(mess);
        Linkify.addLinks(text,Linkify.WEB_URLS);
        text.setLinkTextColor(Color.BLUE);
        text.setLinksClickable(true);
        text.setLayoutParams(llt);
        ll.addView(text);
        mainll.addView(ll);
    }

    public void eventPage() {
        ImageButton game = (ImageButton) findViewById(R.id.gameActivity);
        ImageButton robot = (ImageButton) findViewById(R.id.robotActivity);
        ImageButton code = (ImageButton) findViewById(R.id.codingActivity);
        ImageButton workshop = (ImageButton) findViewById(R.id.workshopActivity);
        ImageButton outofthebox = (ImageButton) findViewById(R.id.outoftheboxActivity);
        ImageButton kaleidoscope = (ImageButton) findViewById(R.id.kaleidoscopeActivity);
        ImageButton geeks = (ImageButton) findViewById(R.id.geeksActivity);
        ImageButton guestlectures = (ImageButton) findViewById(R.id.guestlecturesActivity);
        game.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
                Snackbar sn = Snackbar.make(v, "Gaming", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        robot.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RobotActivity.class));
                Snackbar sn = Snackbar.make(v, "Robotics", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CodeActivity.class));
                Snackbar sn = Snackbar.make(v, "Coding", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        workshop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WorkshopActivity.class));
                Snackbar sn = Snackbar.make(v, "Workshop", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        outofthebox.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OutOfTheBoxActivity.class));
                Snackbar sn = Snackbar.make(v, "Out of the Box", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        kaleidoscope.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KaleidoscopeActivity.class));
                Snackbar sn = Snackbar.make(v, "Kaleidoscope", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        geeks.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GeeksActivity.class));
                Snackbar sn = Snackbar.make(v, "Geeks", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
        guestlectures.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuestLecturesActivity.class));
                Snackbar sn = Snackbar.make(v, "Guest Lectures", Snackbar.LENGTH_SHORT).setAction("Action", null);
                View snackView = sn.getView();
                TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.parseColor("#191500"));
                textView.setBackgroundColor(Color.parseColor("#c7af8d"));
                sn.show();
            }
        });
    }

    public void tapToCall(View view) {
        ImageButton imgbut = (ImageButton) view;
        Intent intent = new Intent(Intent.ACTION_CALL);
        if ((imgbut.getId() + "").compareTo(R.id.call1 + "") == 0) {
            intent.setData(Uri.parse("tel:9007353187"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call2 + "") == 0) {
            intent.setData(Uri.parse("tel:8820099272"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call3 + "") == 0) {
            intent.setData(Uri.parse("tel:8296717518"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call4 + "") == 0) {
            intent.setData(Uri.parse("tel:9883935557"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call5 + "") == 0) {
            intent.setData(Uri.parse("tel:9883238735"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call6 + "") == 0) {
            intent.setData(Uri.parse("tel:9477588826"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call7 + "") == 0) {
            intent.setData(Uri.parse("tel:9832710273"));
        }
        if ((imgbut.getId() + "").compareTo(R.id.call8 + "") == 0) {
            intent.setData(Uri.parse("tel:8334912135"));
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
            if (!callRequestAllowed()) {
                askForCallPermission();
            }
        }
        startActivity(intent);
    }
    public boolean callRequestAllowed()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }
    private void askForCallPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},23);
    }

    public void Firebase()
    {
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference df = fd.getReference("TechInfo");
        ValueEventListener val = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> db = dataSnapshot.getChildren();
                for (DataSnapshot dbval : db) {
                    int id = Integer.parseInt(dbval.child("id").getValue().toString());
                    String mess = dbval.child("message").getValue().toString();
                    String event = dbval.child("event").getValue().toString();
                    DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                    NotifyMessage nm = new NotifyMessage();
                    nm.setId(id);
                    nm.setEventName(event);
                    nm.setMessDesc(mess);
                    if (databaseHandler.addMessage(nm)) {
                        if (mess.contains("404")) {
                            databaseHandler.delete();
                        } else if (mess.contains("504")) {
                            AlertDialog alert=new AlertDialog.Builder(MainActivity.this).create();
                            alert.setTitle("Update");
                            alert.setMessage("You need to uninstall the app and again download it");
                            alert.setIcon(R.drawable.techlogo);
                            alert.setButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            alert.show();
                        } else {
                            note(mess, event);
                        }
                    }
                    LinearLayout mainll = (LinearLayout) findViewById(R.id.main_notify);
                    mainll.removeAllViews();
                    ArrayList<NotifyMessage> noteMessList = new ArrayList<NotifyMessage>();
                    noteMessList = databaseHandler.getAllMessage();
                    for (NotifyMessage noteMess : noteMessList) {
                        if(noteMess.getMessDesc().contains("504")) {
                            AlertDialog alert=new AlertDialog.Builder(MainActivity.this).create();
                            alert.setTitle("Update");
                            alert.setMessage("You need to uninstall the app and again download it");
                            alert.setIcon(R.drawable.techlogo);
                            alert.setButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            alert.show();
                        }
                        else
                        {
                            previousNotification(noteMess.getMessDesc(), noteMess.getEventName());
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addValueEventListener(val);
        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        LinearLayout mainll = (LinearLayout) findViewById(R.id.main_notify);
        mainll.removeAllViews();
        ArrayList<NotifyMessage> noteMessList = new ArrayList<NotifyMessage>();
        noteMessList = databaseHandler.getAllMessage();
        for (NotifyMessage noteMess : noteMessList) {
            if(noteMess.getMessDesc().contains("504")) {
                AlertDialog alert=new AlertDialog.Builder(MainActivity.this).create();
                alert.setTitle("Update");
                alert.setMessage("You need to uninstall the app and again download it");
                alert.setIcon(R.drawable.techlogo);
                alert.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();
            }
            else
            {
                previousNotification(noteMess.getMessDesc(), noteMess.getEventName());
            }
        }
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
}
