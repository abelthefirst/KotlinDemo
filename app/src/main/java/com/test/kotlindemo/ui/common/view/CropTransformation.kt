package com.test.kotlindemo.ui.common.view

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.glide.transformations.CropTransformation

class CropTransformation(cropType: CropType) : CropTransformation(0, 0, cropType) {

    override fun transform(
        context: Context,
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val newToTransform = pool.get(outWidth, outHeight, toTransform.config)
        super.transform(context, pool, newToTransform, outWidth, outHeight)

        return super.transform(context, pool, toTransform, outWidth, outHeight)
    }

}