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

public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gamelist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

    }

    public void gamePage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("Counter Strike 1.6")==0)
        {
            startActivity(new Intent(GameActivity.this,CounterStrike.class));
        }
        if(tw.getText().toString().compareTo("Counter Strike Global Offensive")==0)
        {
            startActivity(new Intent(GameActivity.this,CSGlobalOffensive.class));
        }
        if(tw.getText().toString().compareTo("Mini Militia")==0)
        {
            startActivity(new Intent(GameActivity.this,MiniMilitia.class));
        }
        if(tw.getText().toString().compareTo("Clash of Clans")==0)
        {
            startActivity(new Intent(GameActivity.this,ClashOfClans.class));
        }
        if(tw.getText().toString().compareTo("Defense of the Ancients ( DotA )")==0)
        {
            startActivity(new Intent(GameActivity.this,DOTA.class));
        }
        if(tw.getText().toString().compareTo("FIFA 11 and 14")==0)
        {
            startActivity(new Intent(GameActivity.this,FIFA.class));
        }
        if(tw.getText().toString().compareTo("Need For Speed : Most Wanted")==0)
        {
            startActivity(new Intent(GameActivity.this,NFS.class));
        }

    }
}
