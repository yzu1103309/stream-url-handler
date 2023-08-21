package com.yzu1103309.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        if(data != null)
        {
            TextView vdTXT = (TextView) findViewById(R.id.vd);
            String url = data.toString();
            int pos = url.indexOf("url=");
            if(pos != -1)
            {
                int start_vd, vd_length;
                int start_sub = 0, sub_length = 0;
                boolean sub_found = false;

                start_vd = pos + 4;

                pos = url.indexOf("sub=");
                if(pos != -1)
                {
                    vd_length = pos - 1 - start_vd;
                    start_sub = pos + 4;
                    sub_length = url.length() - start_sub;
                    sub_found = true;
                }
                else
                {
                    vd_length = url.length() - start_vd;
                }
                String vd = url.substring(start_vd, start_vd + vd_length);

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("is.xyz.mpv");
                if(launchIntent != null)
                {
                    launchIntent = new Intent(Intent.ACTION_VIEW);
                    launchIntent.setPackage("is.xyz.mpv");
                    launchIntent.setData(Uri.parse(vd));
                    if(sub_found)
                    {
                        String sub = url.substring(start_sub, start_sub + sub_length);
                        Uri [] subs = {Uri.parse(sub)};
                        launchIntent.putExtra("subs", subs);
                        launchIntent.putExtra("subs.enable", subs);
                    }
                    startActivity(launchIntent);
                }
                else
                {
                    launchIntent = new Intent(Intent.ACTION_VIEW);
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    launchIntent.setData(Uri.parse("market://details?id=" + "is.xyz.mpv"));
                    startActivity(launchIntent);
                }
            }
        }
        finishAndRemoveTask();
    }
}