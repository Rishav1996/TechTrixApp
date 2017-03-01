package techtrix.example.com.techtrix;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        final LinearLayout t=(LinearLayout) findViewById(R.id.splashname);
        final TextView t1=(TextView)findViewById(R.id.splashquote);
        final TextView t2=(TextView)findViewById(R.id.splashhead);
        final ImageView t3=(ImageView)findViewById(R.id.splashlogo);
        t.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        final Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.crossfade);
        /*final VideoView videoView=(VideoView)findViewById(R.id.splashlogo);
        Uri uri=Uri.parse("android.resource://techtrix.example.com.techtrix/"+R.raw.test1);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {*/

                t.startAnimation(anim);
                t1.startAnimation(anim);
                t2.startAnimation(anim);
        t3.setAnimation(anim);
        t.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        t1.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        SplashScreen.this.startActivity(new Intent(SplashScreen.this,MainActivity.class));
                        SplashScreen.this.finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            /*}
        });*/
    }
}
