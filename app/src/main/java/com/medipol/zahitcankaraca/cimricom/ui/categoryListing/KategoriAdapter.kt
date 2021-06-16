package com.medipol.zahitcankaraca.cimricom.ui.categoryListing

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medipol.zahitcankaraca.cimricom.utils.Datas
import com.medipol.zahitcankaraca.cimricom.utils.ItemClickListener
import com.medipol.zahitcankaraca.cimricom.data.KategoriItem
import com.medipol.zahitcankaraca.cimricom.databinding.CardViewKategoriBinding
import com.medipol.zahitcankaraca.cimricom.utils.Base64Util
import com.squareup.okhttp.internal.Util

class KategoriAdapter(
        var kategoriler: ArrayList<KategoriItem>, var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardViewKategoriBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CardViewKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return kategoriler.size
    }

    fun setData(kategorilerYeni: List<KategoriItem>){
        kategoriler = kategorilerYeni as ArrayList<KategoriItem>
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){

            binding.apply {

                binding.edtCardviewKategoriAdi.text = kategoriler[position].KategoriAdi
                binding.imgCardviewKategoriResim.setImageBitmap(Base64Util.base64ToImage(kategoriler[position].ImgResim));

                cardKategoriler.setOnClickListener{
                    Datas.kategoriBilgisi = kategoriler[position].KategoriAdi
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }


}