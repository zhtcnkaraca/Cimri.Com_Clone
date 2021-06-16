package com.medipol.zahitcankaraca.cimricom.ui.detail

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.medipol.zahitcankaraca.cimricom.R
import com.medipol.zahitcankaraca.cimricom.databinding.ActivityCategoryListingBinding
import com.medipol.zahitcankaraca.cimricom.databinding.ActivityDetailBinding
import com.medipol.zahitcankaraca.cimricom.utils.Base64Util
import com.medipol.zahitcankaraca.cimricom.utils.Datas

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init(){
        binding.txtDetayKategori.text = Datas.kategoriBilgisi
        binding.txtDetayUrunAdi.text = Datas.urunAdi
        binding.txtDetayMarka.text = Datas.urunMarka
        binding.txtDetayFiyat.text = Datas.urunFiyat
        binding.txtDetayRenk.text = Datas.urunRenk
        binding.imgDetayResim.setImageBitmap(Base64Util.base64ToImage(Datas.urunResim))
    }
}