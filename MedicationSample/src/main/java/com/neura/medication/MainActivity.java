package com.neura.medication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.neura.medicationaddon.NeuraManager;
import com.neura.standalonesdk.util.SDKUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View loginButton = findViewById(R.id.login_with_neura);

        /**
         * Init Neura instance. If you have an {@link android.app.Application} class, better call
         * {@link NeuraManager#initNeuraConnection(Context, String, String)} from the Application's onCreate.
         */
        NeuraManager.getInstance().initNeuraConnection(getApplicationContext(),
                getString(R.string.app_uid), getString(R.string.app_secret));

        if (SDKUtils.isConnected(this, NeuraManager.getInstance().getNeuraClient())) {
            loginButton.setVisibility(View.GONE);
        } else {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NeuraManager.getInstance().authenticateWithNeura(MainActivity.this, new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {
                            if (message.arg1 == 1) {
                                loginButton.setVisibility(View.GONE);
                            }
                            return false;
                        }
                    });
                }
            });
        }
    }

}
