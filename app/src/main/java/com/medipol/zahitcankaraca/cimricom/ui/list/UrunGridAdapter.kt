package com.medipol.zahitcankaraca.cimricom.ui.list

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medipol.zahitcankaraca.cimricom.utils.Datas
import com.medipol.zahitcankaraca.cimricom.utils.ItemClickListener
import com.medipol.zahitcankaraca.cimricom.data.UrunItem
import com.medipol.zahitcankaraca.cimricom.databinding.CardViewUrunGridBinding
import com.medipol.zahitcankaraca.cimricom.utils.Base64Util

class UrunGridAdapter(
        var urunler: ArrayList<UrunItem>, var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<UrunGridAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CardViewUrunGridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewUrunGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return urunler.size
    }

    override fun onBindViewHolder(holder: UrunGridAdapter.ViewHolder, position: Int) {
        with(holder){

            binding.apply {
                binding.txtCardViewUrunGridName.text = urunler[position].UrunAdi
                binding.txtCardViewUrunGridPrice.text = urunler[position].Fiyat
                binding.imgCardViewUrunGridImage.setImageBitmap(Base64Util.base64ToImage(urunler[position].ImgUrunResim))

                cardUrun.setOnClickListener{
                    Datas.urunResim = urunler[position].ImgUrunResim
                    Datas.urunAdi = urunler[position].UrunAdi
                    Datas.urunMarka = urunler[position].Marka
                    Datas.urunFiyat = urunler[position].Fiyat
                    Datas.urunRenk = urunler[position].Renk
                    onItemClickListener.onItemClick(position)
                }
            }

        }
    }
}