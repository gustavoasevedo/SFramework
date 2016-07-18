package com.dss.sframework.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dss.sframework.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_video)
public class VideoFragment extends Fragment {

    Context context;

    VideoView videoview;

    @AfterViews
    void afterViews() {

        context = getActivity();

        initPlayer();
    }

    public void initPlayer(){
        MediaController mediaController= new MediaController(context);
        mediaController.setAnchorView(videoview);

        Uri uri = Uri.parse("android.resource://"+ context.getPackageName()+"/"+context.getResources().
                getIdentifier("video", "raw", context.getPackageName()));

        videoview.setMediaController(mediaController);

        videoview.setVideoURI(uri);
        videoview.requestFocus();
    }


}
