package com.dss.sframework.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dss.sframework.R;


public class VideoFragment extends Fragment {

    View view;
    Context context;
    VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);

        context = getActivity();

        loadLayout();

        initPlayer();

        return view;
    }

    public void loadLayout(){
        videoView =(VideoView) view.findViewById(R.id.videoview);
    }

    public void initPlayer(){
        MediaController mediaController= new MediaController(context);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("android.resource://"+ context.getPackageName()+"/"+context.getResources().
                getIdentifier("video", "raw", context.getPackageName()));
        
        videoView.setMediaController(mediaController);

        videoView.setVideoURI(uri);
        videoView.requestFocus();
    }


}
