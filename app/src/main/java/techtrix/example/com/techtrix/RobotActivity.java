package techtrix.example.com.techtrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by risha on 27-01-2017.
 */
public class RobotActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robotlist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void robotPage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("Track-o-bot")==0)
        {
            startActivity(new Intent(RobotActivity.this,TrackOBot.class));
        }
        if(tw.getText().toString().compareTo("Kick-o-bot")==0)
        {
            startActivity(new Intent(RobotActivity.this,KickOBot.class));
        }
        if(tw.getText().toString().compareTo("Sprint-o-bot")==0)
        {
            startActivity(new Intent(RobotActivity.this,SpringOBot.class));
        }
        if(tw.getText().toString().compareTo("Transporter")==0)
        {
            startActivity(new Intent(RobotActivity.this,Transporter.class));
        }
        if(tw.getText().toString().compareTo("Battlefield")==0)
        {
            startActivity(new Intent(RobotActivity.this,Battlefield.class));
        }
    }
}
