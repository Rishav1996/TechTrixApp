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
public class OutOfTheBoxActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outoftheboxlist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void outoftheboxPage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("Antakshari")==0)
        {
            startActivity(new Intent(OutOfTheBoxActivity.this,Antakshari.class));
        }
        if(tw.getText().toString().compareTo("Beg-Borrow-Steal")==0)
        {
            startActivity(new Intent(OutOfTheBoxActivity.this,BegBorrowSteal.class));
        }
        if(tw.getText().toString().compareTo("Minute to Win IT")==0)
        {
            startActivity(new Intent(OutOfTheBoxActivity.this,MinuteToWinIt.class));
        }
        if(tw.getText().toString().compareTo("One Shot")==0)
        {
            startActivity(new Intent(OutOfTheBoxActivity.this,OneShot.class));
        }
        if(tw.getText().toString().compareTo("Treasure Hunt")==0)
        {
            startActivity(new Intent(OutOfTheBoxActivity.this,TreasureHunt.class));
        }
    }
}
