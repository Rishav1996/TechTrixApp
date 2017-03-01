package techtrix.example.com.techtrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by risha on 27-01-2017.
 */
public class CodeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.codelist);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void codePage(View view) {
        TextView tw=(TextView)view;
        finish();
        if(tw.getText().toString().compareTo("Bugs Funny")==0)
        {
            startActivity(new Intent(CodeActivity.this,BugsFunny.class));
        }
        if(tw.getText().toString().compareTo("Fill-in")==0)
        {
            startActivity(new Intent(CodeActivity.this,Fillin.class));
        }
        if(tw.getText().toString().compareTo("Circuipedia")==0)
        {
            startActivity(new Intent(CodeActivity.this,Electronics.class));
        }
        if(tw.getText().toString().compareTo("Logia")==0)
        {
            startActivity(new Intent(CodeActivity.this,Logia.class));
        }
    }
}
