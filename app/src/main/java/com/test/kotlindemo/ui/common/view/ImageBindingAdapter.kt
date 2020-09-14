package com.test.kotlindemo.ui.common.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("src")
fun setSrc(imageView: ImageView, src: String?, ) {
    Glide.with(imageView)
        .load(src)
        .transform(CropTransformation(jp.wasabeef.glide.transformations.CropTransformation.CropType.TOP))
        .into(imageView)
}