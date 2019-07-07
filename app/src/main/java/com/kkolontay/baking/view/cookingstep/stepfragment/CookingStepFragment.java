package com.kkolontay.baking.view.cookingstep.stepfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.kkolontay.baking.R;
import com.kkolontay.baking.model.Step;


public class CookingStepFragment extends Fragment {
    private Step step;
    private TextView textView;
    private static final String STEP = "stepsForSaving";
    private static final String CONTEXT = "contextForSaving";
    private PlayerView mPlayerView;
    private ExoPlayer mPlayer;
    private Context context;


    public CookingStepFragment() {
    }

    public CookingStepFragment( Step step) {
        super();
        this.step = step;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        if (savedInstanceState != null) {
            step = savedInstanceState.getParcelable(STEP);

        }
        View rootView = inflater.inflate(R.layout.fragment_cooking_step_item, container, false);
        mPlayerView = rootView.findViewById(R.id.player_view);
        if (step.getThumbnailURL().isEmpty() && step.getVideoURL().isEmpty()) {
            mPlayerView.setVisibility(View.GONE);
        } else {
            if (!step.getVideoURL().isEmpty()) {
                initPlayer(step.getVideoURL());
            } else if (!step.getThumbnailURL().isEmpty()) {
                initPlayer(step.getThumbnailURL());
            }
        }
        textView = rootView.findViewById(R.id.step_cooking_description_text_view);
        textView.setText(step.getDescription());
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STEP, step);
        super.onSaveInstanceState(outState);
    }

    private void initPlayer(String url) {
        if (url == null) {
            mPlayerView.setVisibility(View.GONE);
            return;

        }
        Uri uri = Uri.parse(url);
        if (uri == null) {
            mPlayerView.setVisibility(View.GONE);
            return;
        } else {
            mPlayerView.setVisibility(View.VISIBLE);
        }
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        mPlayerView.setPlayer(mPlayer);
        String userAgent = Util.getUserAgent(context, step.getShortDescription());
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, userAgent);
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        mPlayer.prepare(videoSource);
        mPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}
