package com.dss.sframework.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import com.dss.sframework.R;
import com.dss.sframework.helper.PagerActivityHelper;


public class PagerActivity extends ActionBarActivity {

    private Context context;;
    PagerActivityHelper pagerActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        context = this;
        pagerActivityHelper = new PagerActivityHelper();
        pagerActivityHelper.InitBar(context);
        pagerActivityHelper.PagerActivity(context);
        pagerActivityHelper.startPager(context);

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
            case R.id.action_reload:;
                //  Toast.makeText(MainActivity.this,"Recarrega",Toast.LENGTH_LONG).show();
                break;

            case R.id.action_close:;
                Intent i = new Intent(context,MainActivity.class);

                context.startActivity(i);
                PagerActivity.this.finish();
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

}