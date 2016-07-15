package com.dss.sframework.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.constant.ConstantNavigationDrawer;
import com.dss.sframework.delegate.UpdateDelegate;
import com.dss.sframework.fragment.FragmentStarter;
import com.dss.sframework.navigation.NavigationDrawer;
import com.dss.sframework.tasks.UserSyncTask;
import com.dss.sframework.util.DeviceInfo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements UpdateDelegate {

    @ViewById
    TextView textVersionNumber;

    @ViewById
    TextView textVersionName;

    @ViewById
    Toolbar tool_bar;



    @AfterViews
    void afterViews() {

        startNavigation();

        setText();

        FragmentStarter.startDemoFragment(this);

    }

    @Background
    void doTask(){
        UserSyncTask gProdTask = new UserSyncTask(4,this);
        gProdTask.execute();
    }



    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();
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
            case R.id.action_reload:
                //  Toast.makeText(MainActivity.this,"Recarrega",Toast.LENGTH_LONG).show();
                break;

            case R.id.action_close:
                finish();
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void sucessoUpdate(boolean sucesso) {
        if(sucesso) {
            Toast.makeText(this, "Sucesso ao carregar Dados", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Erro ao carregar Dados", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ErroUpdate(Exception e) {
        Log.e("MainActivity", e.toString());
        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void startNavigation(){
        // Attaching the layout to the menu_toolbar object
        this.setSupportActionBar(tool_bar); // Setting menu_toolbar as the ActionBar with setSupportActionBar() call

        NavigationDrawer navigationDrawer = new NavigationDrawer(
                ConstantNavigationDrawer.TITLES,
                ConstantNavigationDrawer.ICONS,
                ConstantNavigationDrawer.NAME,
                ConstantNavigationDrawer.EMAIL,
                ConstantNavigationDrawer.PROFILE,
                this);


        navigationDrawer.initDrawer(this, tool_bar);
    }


    public void setText(){
        DeviceInfo deviceInfo = new DeviceInfo(this);
        textVersionNumber.setText("Version Number: " + String.valueOf(deviceInfo.getVersionCode()));
        textVersionName.setText("Version Name: " +  deviceInfo.getVersionName());

    }

}



