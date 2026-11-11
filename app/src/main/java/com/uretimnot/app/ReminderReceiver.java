package com.uretimnot.app;
import android.app.*;
import android.content.*;
import android.os.Build;
import org.json.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReminderReceiver extends android.content.BroadcastReceiver {
 static final int ID=745;
 static void schedule(Context context){
  Calendar c=Calendar.getInstance();c.set(Calendar.HOUR_OF_DAY,7);c.set(Calendar.MINUTE,45);c.set(Calendar.SECOND,0);c.set(Calendar.MILLISECOND,0);
  if(!c.after(Calendar.getInstance()))c.add(Calendar.DATE,1);
  while(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)c.add(Calendar.DATE,1);
  Intent i=new Intent(context,ReminderReceiver.class);
  PendingIntent pi=PendingIntent.getBroadcast(context,ID,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
  // setAlarmClock is permitted without SCHEDULE_EXACT_ALARM; Android shows an alarm indicator.
  am.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),PendingIntent.getActivity(context,ID,new Intent(context,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE)),pi);
 }
 @Override public void onReceive(Context context,Intent intent){
  try {
   Calendar now=Calendar.getInstance();int dow=now.get(Calendar.DAY_OF_WEEK);
   if(dow!=Calendar.SATURDAY&&dow!=Calendar.SUNDAY){
    String today=new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(now.getTime());
    JSONArray a=new JSONArray(context.getSharedPreferences("uretimnot",Context.MODE_PRIVATE).getString("tasks","[]"));
    int count=0;StringBuilder names=new StringBuilder();
    for(int k=0;k<a.length();k++){JSONObject t=a.optJSONObject(k);if(t!=null&&!t.optBoolean("done",false)&&today.equals(t.optString("date"))){count++;if(names.length()<110){if(names.length()>0)names.append(" • ");names.append(t.optString("title","İş"));}}}
    NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    if(Build.VERSION.SDK_INT>=26)nm.createNotificationChannel(new NotificationChannel("daily","Günlük İşler",NotificationManager.IMPORTANCE_DEFAULT));
    Intent open=new Intent(context,MainActivity.class);
    PendingIntent click=PendingIntent.getActivity(context,4,open,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
    String message=count==0?"Bugüne planlanan açık iş bulunmuyor.":count+" planlı iş: "+names;
    Notification.Builder b=Build.VERSION.SDK_INT>=26?new Notification.Builder(context,"daily"):new Notification.Builder(context);
    b.setSmallIcon(android.R.drawable.ic_menu_agenda).setContentTitle("Günlük İşler • 07:45").setContentText(message).setStyle(new Notification.BigTextStyle().bigText(message)).setContentIntent(click).setAutoCancel(true);
    nm.notify(ID,b.build());
   }
  }catch(Exception ignored){}finally{schedule(context);}
 }
}
