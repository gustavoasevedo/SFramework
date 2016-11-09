package com.dss.sframework.view.fragment;

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
import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.model.dto.TestObjectDTO;
import com.dss.sframework.tools.constant.ConststantAD;
import com.dss.sframework.tools.factories.ImageFactory;
import com.dss.sframework.tools.factories.JsonFactory;
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
            TestObjectDTO TestObjectDTO = TestObjectDao.getInstance(context).selectId(1);
            Toast.makeText(context, TestObjectDTO.toString(), Toast.LENGTH_LONG).show();
        }


    @Click(R.id.btInsert)
    void insertClick(){

            TestObjectDTO TestObjectDTO = TestObjectDao.getInstance(context).selectId(3);
            ArrayList<TestObjectDTO> insert= new ArrayList<>();
            insert.add(TestObjectDTO);

            TestObjectDao.getInstance(context).insertListObject(insert);

            Toast.makeText(context,"Inserido com sucesso",Toast.LENGTH_LONG).show();

    }

    @Click(R.id.btnJsonList)
    void jsonListClick(){
        TestObjectDTO TestObjectDTO = TestObjectDao.getInstance(context).selectId(2);
        TestObjectDTO TestObjectDTO2 = TestObjectDao.getInstance(context).selectId(5);
        TestObjectDTO TestObjectDTO3 = TestObjectDao.getInstance(context).selectId(7);

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(TestObjectDTO);
        arrayList.add(TestObjectDTO2);
        arrayList.add(TestObjectDTO3);

        String result = buildJsonList(arrayList);

        //Desserialize a list
//            Gson serializer = new Gson();
//            TestObjectDTOList TestObjectDTOList = serializer.fromJson(TestObjectDTOList.setHeaderJson("TestObjectDTOList", result), TestObjectDTOList.class);

        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }

    @Click(R.id.btnJson)
    void jsonClick (){
            TestObjectDTO testObjectDTO = TestObjectDao.getInstance(context).selectId(5);

            String json = buildJson(testObjectDTO).toString();

            //Desserialize one item
//            Gson serializer = new Gson();
//            TestObjectDTOList TestObjectDTOList = serializer.fromJson(TestObjectDTOList.setHeaderJson("TestObjectDTOList", "[" + json + "]"), TestObjectDTOList.class);

            Toast.makeText(context,json,Toast.LENGTH_LONG).show();
        }


    @Click(R.id.btntoBase64)
    void toBase64Click(){

            Toast.makeText(context,buildBase64(),Toast.LENGTH_LONG).show();

            btnfromBase64.setVisibility(View.VISIBLE);

        }

    @Click(R.id.btnfromBase64)
    void fromBase64Click(){
            Drawable thumb = buildThumb();

            imgBase64.setImageDrawable(thumb);
    }

    @Click(R.id.btnIntent)
    void intentClick(){
            Toast.makeText(context, "com.dss.sframework.view.fragment.DemoFragment",Toast.LENGTH_LONG).show();
    }

    @Click(R.id.imgBase64)
    void imageClick(){

            ImageFactory.showDialogImage(context, "Teste", "http://icons.iconarchive.com/icons/carlosjj/google-jfk/128/android-icon.png");
    }

    public String buildJson(TestObjectDTO testObjectDTO){
        Object send = testObjectDTO;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = JsonFactory.getJsonObject(testObjectDTO);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public String buildJsonList(ArrayList<Object> arrayList){
        JSONObject json= new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            for(Object i:arrayList){
                jsonArray.put(JsonFactory.getJsonObject(i));
            }
            json.put("Lista",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray.toString();
    }

    public String buildBase64(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        String base64 = ImageFactory.convertPhotoToBase64(bitmap);

        image = base64;

        return base64;
    }

    public Drawable buildThumb(){
        Bitmap bitmap = ImageFactory.convertPhotoFromBase64(image);

        Drawable thumb = new BitmapDrawable(getResources(), bitmap);

        return thumb;
    }

}