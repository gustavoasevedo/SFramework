package com.dss.sframework.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.constant.ConststantAD;
import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.factories.ImageFactory;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.model.TestObject;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
@EFragment(R.layout.fragment_buttons)
public class DemoFragment extends Fragment {

    Context context;
    String image;

    @ViewById
    Button btInsert;

    @ViewById
    Button btSelect;

    @ViewById
    Button btnJson;

    @ViewById
    Button btnJsonList;

    @ViewById
    Button btntoBase64;

    @ViewById
    Button btnfromBase64;

    @ViewById
    Button btnIntent;

    @ViewById
    ImageView imgBase64;

    @ViewById
    AdView adView;

    @AfterViews
    void afterViews() {

        context = getActivity();
        loadAd();

    }

    public void loadAd(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(ConststantAD.banner_ad_unit_id)
                .build();
        adView.loadAd(adRequest);
    }


    @Click(R.id.btSelect)
    void selectClick(){
            TestObject testObject = TestObjectDao.getInstance(context).selectId(1);
            Toast.makeText(context, testObject.toString(), Toast.LENGTH_LONG).show();
        }


    @Click(R.id.btInsert)
    void insertClick(){

            TestObject testObject = TestObjectDao.getInstance(context).selectId(3);
            ArrayList<TestObject> insert= new ArrayList<>();
            insert.add(testObject);

            TestObjectDao.getInstance(context).insertListObject(insert);

            Toast.makeText(context,"Inserido com sucesso",Toast.LENGTH_LONG).show();

    }

    @Click(R.id.btnJsonList)
    void jsonListClick(){
            TestObject testObject = TestObjectDao.getInstance(context).selectId(2);
            TestObject testObject2 = TestObjectDao.getInstance(context).selectId(5);
            TestObject testObject3 = TestObjectDao.getInstance(context).selectId(7);

            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(testObject2);
            arrayList.add(testObject3);


            Object send = testObject;
            JSONObject json= null;

            try {
                json = JsonFactory.getJsonObject(send);
                JSONArray jsonArray = new JSONArray();
                for(Object i:arrayList){
                    jsonArray.put(JsonFactory.getJsonObject(i));
                }

                json.put("Lista",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result = json.toString();

            //Desserialize a list
//            Gson serializer = new Gson();
//            TestObjectList testObjectList = serializer.fromJson(TestObjectList.setHeaderJson("TestObjectList", result), TestObjectList.class);

            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }

    @Click(R.id.btnJson)
    void jsonClick (){
            TestObject testObject = TestObjectDao.getInstance(context).selectId(5);

            Object send = testObject;
            JSONObject jsonObject = null;
            try {

                jsonObject = JsonFactory.getJsonObject(send);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String json = jsonObject.toString();

            //Desserialize one item
//            Gson serializer = new Gson();
//            TestObjectList testObjectList = serializer.fromJson(TestObjectList.setHeaderJson("TestObjectList", "[" + json + "]"), TestObjectList.class);


            Toast.makeText(context,json,Toast.LENGTH_LONG).show();
        }

    public View.OnClickListener toBase64Click = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            String base64 = ImageFactory.convertPhotoToBase64(bitmap);

            image = base64;

            Toast.makeText(context,base64,Toast.LENGTH_LONG).show();

            btnfromBase64.setVisibility(View.VISIBLE);

        }
    };

    @Click(R.id.btnfromBase64)
    void fromBase64Click(){

            Bitmap bitmap = ImageFactory.convertPhotoFromBase64(image, context);

            Drawable thumb = new BitmapDrawable(getResources(), bitmap);

            imgBase64.setImageDrawable(thumb);
    }

    @Click(R.id.btnIntent)
    void intentClick(){
            Toast.makeText(context, "com.dss.sframework.fragment.DemoFragment",Toast.LENGTH_LONG).show();
    }

    @Click(R.id.imgBase64)
    void imageClick(){

            ImageFactory.showDialogImage(context, "Teste", "http://icons.iconarchive.com/icons/carlosjj/google-jfk/128/android-icon.png");
    }

}