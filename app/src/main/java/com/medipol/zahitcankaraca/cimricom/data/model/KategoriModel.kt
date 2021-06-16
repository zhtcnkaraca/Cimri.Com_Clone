package com.medipol.zahitcankaraca.cimricom.data

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@Keep
@IgnoreExtraProperties
class KategoriItem{

    @PropertyName("ImgResim")
    val ImgResim: String?=null

    @PropertyName("KategoriAdi")
    val KategoriAdi: String?=null


    override fun toString(): String {
        return "KategoriItem(ImgResim=$ImgResim, KategoriAdi=$KategoriAdi)"
    }
}

