package com.neura.medication;

import android.content.Context;

import com.neura.medication.R;
import com.neura.medicationaddon.ActionPillsReceiver;

/**
 * When an event is received from neura(userWokeUp, userGotUp, userLeftHome) there's a default behaviour
 * of displaying a notification with a default text, for example : when neura detects 'userWokeUp',
 * we'll be displaying a notification for taking your morning pill,
 * it looks like this : https://s17.postimg.org/69i9g6ky7/Good_Morning_Pill.png
 * The notification is done from {@link ActionPillsReceiver}, on
 * {@link ActionPillsReceiver#handlePillActionReceived(Context, String)}.
 * You have 2 options :
 * 1. If you want {@link ActionPillsReceiver} to show the notifications for you :
 * a. Define SamplePillsReceiver which extends {@link ActionPillsReceiver}, and define this receiver
 * in your AndroidManifest file like so :
 * <receiver android:name=".activity.SamplePillsReceiver">
 * <intent-filter>
 * <action android:name="com.neura.medicationaddon.MorningPill" />
 * <action android:name="com.neura.medicationaddon.EveningPill" />
 * <action android:name="com.neura.medicationaddon.PillBoxReminder" />
 * <p>
 * <action android:name="com.neura.medication.ACTION_NOTIFICATION_TOOK_PILLS" />
 * <action android:name="com.neura.medication.ACTION_NOTIFICATION_REMIND_ME_LATER" />
 * <p>
 * </intent-filter>
 * </receiver>
 * b. override the methods : {@link ActionPillsReceiver#getNotificationSmallIcon(Context)},
 * {@link ActionPillsReceiver#getMorningPillIcon(Context)}, {@link ActionPillsReceiver#getEveningPillIcon(Context)},
 * {@link ActionPillsReceiver#getPillBoxReminderIcon(Context)}, and give the icons you wish to set
 * in the notification.
 * 2. If you don't want to use the default notification display, override the method
 * {@link ActionPillsReceiver#handlePillActionReceived(Context, String)}
 * and handle the events yourself. In this case, you don't need to override the methods from section 1b.
 */
public class SamplePillsReceiver extends ActionPillsReceiver {

    /**
     * Option 1 - let Neura handle the events, and set your own icons to be displayed in the notification.
     */
    @Override
    protected int getNotificationSmallIcon(Context context) {
        return R.mipmap.ic_launcher;
    }

    @Override
    protected int getMorningPillIcon(Context context) {
        return R.mipmap.morningicon;
    }

    @Override
    protected int getEveningPillIcon(Context context) {
        return R.mipmap.evening_icon;
    }

    @Override
    protected int getPillBoxReminderIcon(Context context) {
        return R.mipmap.pillboxicon;
    }

    /**
     * Option 2 - implement your own mechanism for handling events received from Neura.
     * This is just a sample, so it's not doing anything, and calling the base receiver.
     */
    @Override
    protected void handlePillActionReceived(Context context, String actionPill) {
        super.handlePillActionReceived(context, actionPill);
    }

}
