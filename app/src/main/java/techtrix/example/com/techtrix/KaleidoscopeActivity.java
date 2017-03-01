package techtrix.example.com.techtrix;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by risha on 27-01-2017.
 */
public class KaleidoscopeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaleidoscopelist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void kaliedoscopePage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("Behind the lens")==0)
        {
            startActivity(new Intent(KaleidoscopeActivity.this,BehindTheLens.class));
        }
        if(tw.getText().toString().compareTo("Canvas")==0)
        {
            startActivity(new Intent(KaleidoscopeActivity.this,Canvas.class));
        }
        if(tw.getText().toString().compareTo("Director's cut")==0)
        {
            startActivity(new Intent(KaleidoscopeActivity.this,DirectorsCut.class));
        }
        if(tw.getText().toString().compareTo("Impact")==0)
        {
            startActivity(new Intent(KaleidoscopeActivity.this,Impact.class));
        }
        if(tw.getText().toString().compareTo("Logo Designing in Photoshop")==0)
        {
            startActivity(new Intent(KaleidoscopeActivity.this,LogoDesigning.class));
        }
        if(tw.getText().toString().compareTo("T-shirt Painting")==0)
        {
            startActivity(new Intent(KaleidoscopeActivity.this,TShirtPainting.class));
        }
    }
}
