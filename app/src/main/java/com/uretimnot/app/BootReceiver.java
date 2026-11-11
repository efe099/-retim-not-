package com.uretimnot.app;
import android.content.*;
public class BootReceiver extends BroadcastReceiver {
 @Override public void onReceive(Context context,Intent intent){ReminderReceiver.schedule(context);}
}
