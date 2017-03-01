package techtrix.example.com.techtrix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by risha on 04-02-2017.
 */

public class BackGroundService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(FirebaseService.class.getName()));
    }
}
