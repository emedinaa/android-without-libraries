package com.emedinaa.meetupapp.common.media;

import android.widget.ImageView;

/**
 * Created by emedinaa on 16/04/16.
 */
public interface ImageLoader {

    void load(String url, ImageView imageView);
    void loadLocal(String path, ImageView imageView);
}
