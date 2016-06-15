package com.dss.sframework.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.delegate.UpdateDelegate;
import com.dss.sframework.fragment.FragmentStarter;
import com.dss.sframework.helper.MainHelper;
import com.dss.sframework.tasks.UserSyncTask;


public class MainActivity extends ActionBarActivity implements UpdateDelegate {

    MainHelper helper;
    Context context;

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        UserSyncTask gProdTask = new UserSyncTask(4,this);
        gProdTask.execute();

        helper = new MainHelper();
        helper.MainActivity(context);
        helper.startNavigation(context);
        helper.setText(context);
        FragmentStarter.startDemoFragment(context);


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
            Toast.makeText(context, "Sucesso ao carregar Dados", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Erro ao carregar Dados", Toast.LENGTH_LONG).show();
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

    @Override
    public TextView getTVTitulo() {
        return null;
    }

    @Override
    public TextView getTVPorcentagem() {
        return null;
    }
}



