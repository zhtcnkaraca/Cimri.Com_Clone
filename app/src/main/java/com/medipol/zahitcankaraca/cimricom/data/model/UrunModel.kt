package com.medipol.zahitcankaraca.cimricom.data

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@Keep
@IgnoreExtraProperties
class UrunItem{

    @PropertyName("Marka")
    val Marka: String?=null

    @PropertyName("ImgUrunResim")
    val ImgUrunResim: String?=null

    @PropertyName("KategoriBilgisi")
    val KategoriBilgisi: String?=null

    @PropertyName("Renk")
    val Renk: String?=null

    @PropertyName("UrunAdi")
    val UrunAdi: String?=null

    @PropertyName("Fiyat")
    val Fiyat: String?=null


    override fun toString(): String {
        return "KategoriItem(Marka=$Marka, ImgUrunResim=$ImgUrunResim,KategoriBilgisi=$KategoriBilgisi , Renk=$Renk,UrunAdi=$UrunAdi,Fiyat=$Fiyat)"
    }
}