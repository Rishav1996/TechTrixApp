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
public class GeeksActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geekslist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void geeksPage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("BizTech")==0)
        {
            startActivity(new Intent(GeeksActivity.this,BizTech.class));
        }
        if(tw.getText().toString().compareTo("Mathemagic")==0)
        {
            startActivity(new Intent(GeeksActivity.this,MatheMagic.class));
        }
        if(tw.getText().toString().compareTo("Question Mark")==0)
        {
            startActivity(new Intent(GeeksActivity.this,QuestionMark.class));
        }
        if(tw.getText().toString().compareTo("SpellBee")==0)
        {
            startActivity(new Intent(GeeksActivity.this,SpellBee.class));
        }
        if(tw.getText().toString().compareTo("Sudoku")==0)
        {
            startActivity(new Intent(GeeksActivity.this,Sudoku.class));
        }
        if(tw.getText().toString().compareTo("Verbose")==0)
        {
            startActivity(new Intent(GeeksActivity.this,Verbose.class));
        }
    }
}
