package org.scoutant.blokish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SplashScreen extends Activity {
    private static final long DELAY = 1500;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivityForResult(new Intent(SplashScreen.this, UI.class), 0);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);
        long delai = DELAY;
        try {
            FileInputStream fis;
            fis = openFileInput("moves.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            if (reader.readLine() != null && reader.readLine() != null) {
                delai = DELAY / 3;
            }
        } catch (Exception ignored) {
        }
        handler.sendEmptyMessageDelayed(0, delai);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }

}
