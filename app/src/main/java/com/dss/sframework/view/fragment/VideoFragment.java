package com.dss.sframework.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dss.sframework.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_video)
public class VideoFragment extends Fragment {

    Context context;

    @ViewById
    VideoView videoview;

    @AfterViews
    void afterViews() {

        context = getActivity();

        initPlayer();
    }

    public void initPlayer(){

        MediaController mediaController = configureControler();

        Uri uri = configureURI();

        configureVideo(uri,mediaController);

    }

    public MediaController configureControler(){
        MediaController mediaController= new MediaController(context);
        mediaController.setAnchorView(videoview);

        return mediaController;

    }

    public Uri configureURI(){
        Uri uri = Uri.parse("android.resource://"+ context.getPackageName()+"/"+context.getResources().
                getIdentifier("video", "raw", context.getPackageName()));

        return uri;
    }

    public void configureVideo(Uri uri, MediaController mediaController){
        videoview.setVideoURI(uri);
        videoview.setMediaController(mediaController);
        videoview.requestFocus();
    }

}
