package techtrix.example.com.techtrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by risha on 27-01-2017.
 */
public class WorkshopActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshoplist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void workshopPage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("Android")==0)
        {
            startActivity(new Intent(WorkshopActivity.this,Android.class));
        }
        if(tw.getText().toString().compareTo("Advance Internet of Things (IoT)")==0)
        {
            startActivity(new Intent(WorkshopActivity.this,iot.class));
        }
        if(tw.getText().toString().compareTo("Biped Robotics")==0)
        {
            startActivity(new Intent(WorkshopActivity.this,BipedRobotics.class));
        }
    }
}
