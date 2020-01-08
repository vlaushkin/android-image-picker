package com.esafirm.imagepicker.features.imageloader;

import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.esafirm.imagepicker.R;
import com.esafirm.imagepicker.model.Image;

public class DefaultImageLoader implements ImageLoader {

    @Override
    public void loadImage(Image image, ImageView imageView, ImageType imageType) {
        Object source;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            source = image.getUri();
        } else {
            source = image.getPath();
        }

        Glide.with(imageView.getContext())
                .load(source)
                .apply(RequestOptions
                        .placeholderOf(imageType == ImageType.FOLDER
                                ? R.drawable.ef_folder_placeholder
                                : R.drawable.ef_image_placeholder)
                        .error(imageType == ImageType.FOLDER
                                ? R.drawable.ef_folder_placeholder
                                : R.drawable.ef_image_placeholder)
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }
}
