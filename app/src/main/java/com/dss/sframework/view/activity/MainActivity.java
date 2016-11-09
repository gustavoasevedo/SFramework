package com.dss.sframework.view.activity;

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
import com.dss.sframework.controler.tasks.ListUserSyncTask;
import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.tools.constant.ConstantNavigationDrawer;
import com.dss.sframework.tools.delegate.UpdateDelegate;
import com.dss.sframework.tools.util.DeviceInfo;
import com.dss.sframework.view.fragment.FragmentStarter;
import com.dss.sframework.view.navigation.NavigationDrawer;

import org.androidannotations.annotations.AfterViews;
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

        new ListUserSyncTask(0, this).execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_reload:
                new ListUserSyncTask(0, this).execute();
                break;

            case R.id.action_close:
                TestObjectDao.getInstance(this).drop();
                finish();
                break;
        }
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
        this.setSupportActionBar(tool_bar);

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



