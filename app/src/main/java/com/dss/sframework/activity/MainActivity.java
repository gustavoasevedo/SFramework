package com.dss.sframework.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dss.sframework.broadcast.ParsePushBroadcastReceiver;
import com.dss.sframework.helper.MainHelper;
import com.dss.sframework.R;
import com.dss.sframework.constant.ConstantIntent;
import com.dss.sframework.util.MintUtils;


public class MainActivity extends ActionBarActivity {

    MainHelper helper;

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();
        ParsePushBroadcastReceiver.changeNotificationIconBegin(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParsePushBroadcastReceiver.setLastEnabled(ConstantIntent.getMAIN(), MainActivity.this);
        MintUtils.mintStart(MainActivity.this);

        helper = new MainHelper();
        helper.MainActivity(this);
        helper.startNavigation(this);
        helper.setText(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_search:;
              //  Toast.makeText(MainActivity.this,"Busca",Toast.LENGTH_LONG).show();
            break;

            case R.id.action_reload:;
                //  Toast.makeText(MainActivity.this,"Recarrega",Toast.LENGTH_LONG).show();
                break;

            case R.id.action_close:;
                finish();
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


}



