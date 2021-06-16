package com.medipol.zahitcankaraca.cimricom.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

object Base64Util {
    fun base64ToImage(image: String?): Bitmap? {
        val imageBytes = Base64.decode(image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size);
    }
}