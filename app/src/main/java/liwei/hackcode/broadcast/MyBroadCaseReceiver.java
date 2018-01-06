package liwei.hackcode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by liwei on 2018/1/1.
 */

public class MyBroadCaseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "receive in MyBroadcaseReceiver.", Toast.LENGTH_SHORT).show();
    }
}
