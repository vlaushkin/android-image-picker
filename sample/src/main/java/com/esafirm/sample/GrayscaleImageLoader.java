package com.esafirm.sample;

import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.esafirm.imagepicker.features.imageloader.ImageLoader;
import com.esafirm.imagepicker.features.imageloader.ImageType;
import com.esafirm.imagepicker.model.Image;

public class GrayscaleImageLoader implements ImageLoader {

    private static final RequestOptions REQUEST_OPTIONS = new RequestOptions().transform(new GrayscaleTransformation());

    @Override
    public void loadImage(Image image, ImageView imageView, ImageType imageType) {
        Object source;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            source = image.getUri();
        } else {
            source = image.getPath();
        }

        Glide.with(imageView)
                .asBitmap()
                .load(source)
                .transition(BitmapTransitionOptions.withCrossFade())
                .apply(REQUEST_OPTIONS)
                .into(imageView);
    }
}
