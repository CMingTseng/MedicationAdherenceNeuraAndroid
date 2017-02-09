##MedicationAdherenceNeuraAndroid
* This project contains an addon which uses [NeuraSdk](https://dev.theneura.com/docs/guide/android/setup).
* Say you have a medication application, you can receive smart reminders for taking a morning pill, an evening pill and take the pillbox when you leave home. By utilizing Neura's smart reminders, you can distinguish your services from competitors. <br/><b>Instead of being sent on a fixed time, alerts come to your user at just the right moment, ensuring their relevance.</b>

<img src="https://s4.postimg.org/4nzaecdvx/morning.png" alt="morning" width="280" height="498">
<img src="https://s16.postimg.org/u74m3u351/pillbox.png" alt="pillbox" width="280" height="498">
<img src="https://s18.postimg.org/5tg2oz0i1/evening.png" alt="evening" width="280" height="498">

##Requirements 
1. Basic android knowledge.
2. [Android studio](https://developer.android.com/studio/index.html) installed.

##Down to business
MedicationAdherenceNeuraAndroid is divided to 2 inner projects : 
* [MedicationNeuraAddon](https://github.com/NeuraLabs/MedicationAdherenceNeuraAndroid/tree/master/MedicationNeuraAddon) : Library project, containing logic for registering to Neura API, receiving events from Neura, and displaying the pillbox reminders as displayed above.
* [MedicationSample](https://github.com/NeuraLabs/MedicationAdherenceNeuraAndroid/tree/master/MedicationSample): Sample project using MedicatiNeurAddon library

##Setting this application to be your own
If you wish to integrate MedicationNeuraAddon to your own projects(which is basically what [MedicationSample](https://github.com/NeuraLabs/MedicationAdherenceNeuraAndroid/tree/master/MedicationSample) does), follow these steps:

1. [Create your application](https://dev.theneura.com/console/new)
  * Make sure that under 'Tech Info' (2nd section) you're specifying your own 'Application Package Name'. 
  * In order to receive events (which is listed below) from Neura, define your 'Android Push Credentials' using [push notification](https://dev.theneura.com/docs/guide/android/pushnotification).
  * Under 'Permissions' select the events : 'Whenever a user wakes up', 'Whenever a user gets up', 'Whenever a user is about to sleep' and 'Whenever a user leaves home'.

2. Pull this git project to your local environment : <br/>```git init```<br/>```git clone https://github.com/NeuraLabs/MedicationAdherenceNeuraAndroid.git```

3. Import the addon to your project by selecting in the Android Studio menu : File -> New -> Import Module.

4. Add to your project's gradle file : 
  ```
  dependencies {
      compile project(':MedicationNeuraAddon')
      compile 'com.theneura:android-sdk:+'
  }
  ```
  
5. Handling take pill reminders
  * Define a BroadcastReceiver in the AndoridManifest, with the following intents : 
       ```
        <receiver android:name=".SamplePillsReceiver">
            <intent-filter>
                <action android:name="com.neura.medicationaddon.MorningPill" />
                <action android:name="com.neura.medicationaddon.EveningPill" />
                <action android:name="com.neura.medicationaddon.PillBoxReminder" />

                <action android:name="com.neura.medication.ACTION_NOTIFICATION_TOOK_PILLS" />
                <action android:name="com.neura.medication.ACTION_NOTIFICATION_REMIND_ME_LATER" />

            </intent-filter>
        </receiver>
       ```
       
  * Create the class [SamplePillsReceiver](https://github.com/NeuraLabs/MedicationAdherenceNeuraAndroid/blob/master/MedicationSample/src/main/java/com/neura/medication/SamplePillsReceiver.java), which extends ActionPillsReceiver.<br/>
<b>You have 2 options:</b>      
  
          a.  ActionPillsReceiver showing the notifications for you. If you want to set the icons to be displayed to the notification, override : ```getNotificationSmallIcon```, ```getMorningPillIcon```,  ```getEveningPillIcon```, ```getPillBoxReminderIcon```.
  
          b.  If you don't want to use the default notification display, override the method ```handlePillActionReceived``` and handle the morning, evening and pills reminder yourself. 

6. Call ```NeuraManager.getInstance().initNeuraConnection(getApplicationContext(), YOUR_APP_ID, YOUR_APP_SECRET)``` when your application starts : YOUR_APP_ID & YOUR_APP_SECRET can be received from <a href="https://dev.theneura.com/console/">Applications console</a>, under the application you've just created.

7. Call ```NeuraManager.getInstance().authenticateWithNeura(...)``` from your code in order to login to Neura. 
<br/>Fyi, It takes Neura few days to detect where your home is since it takes time to process your habits. Therefore, the 'take your pillbox' notification will be displayed after few days.

8. That's it, you're done.

##Testing while developing
Obviously, it's not very convenient for you to detect when events occur on realtime, so, Neura has generated 
an [events simulation](http://docs.theneura.com/android/com/neura/standalonesdk/service/NeuraApiClient.html#simulateAnEvent--), and you can connect it with your application by calling : ```NeuraManager.getInstance().getNeuraClient().simulateAnEvent();```

##Support
1. Go to <a href="https://dev.theneura.com/docs/getstarted">getting started with Neura</a> for more details.
2. You can read classes and API methods at <a href ="http://docs.theneura.com/android/com/neura/standalonesdk/service/NeuraApiClient.html">Neura Sdk Reference</a>.
3. You can ask question and view existing questions with the Neura tag on <a href="https://stackoverflow.com/questions/tagged/neura?sort=newest&pageSize=30">StackOverflow</a>.
