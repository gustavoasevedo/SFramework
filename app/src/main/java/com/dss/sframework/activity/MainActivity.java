package com.dss.sframework.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dss.sframework.adapter.NavigationDrawerAdapter;
import com.dss.sframework.dao.TestTable;
import com.dss.sframework.factories.ImageFactory;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.helper.MainHelper;
import com.dss.sframework.navigation.NavigationDrawer;
import com.dss.sframework.objects.TestObject;
import com.dss.sframework.R;
import com.dss.sframework.util.ConstantNavigationDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startNavigation();



    }

    public void startNavigation(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar); // Setting toolbar as the ActionBar with setSupportActionBar() call

        NavigationDrawer navigationDrawer = new NavigationDrawer(ConstantNavigationDrawer.getTITLES(),ConstantNavigationDrawer.getICONS(),ConstantNavigationDrawer.getNAME(),
                ConstantNavigationDrawer.getEMAIL(),ConstantNavigationDrawer.getPROFILE(),this);
        navigationDrawer.initDrawer(this, toolbar);
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

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}



