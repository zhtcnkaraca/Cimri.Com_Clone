package com.medipol.zahitcankaraca.cimricom.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.medipol.zahitcankaraca.cimricom.R
import com.medipol.zahitcankaraca.cimricom.data.*
import com.medipol.zahitcankaraca.cimricom.databinding.ActivityListingBinding
import com.medipol.zahitcankaraca.cimricom.utils.LoadingDialog
import com.medipol.zahitcankaraca.cimricom.ui.detail.DetailActivity
import com.medipol.zahitcankaraca.cimricom.utils.ItemClickListener

class ListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListingBinding
    var urunViewHolder = UrunViewHolder()
    private lateinit var urunGridAdapter : UrunGridAdapter
    val loading = LoadingDialog(this)
    var yeniUrunList: List<UrunItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init(){
        loading.startLoading()
        urunleriAl()

        ArrayAdapter.createFromResource(
            this, R.array.filter_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spFiltre.adapter = adapter
        }

    }

    fun filter(urunList: List<UrunItem>?) {
        urunList?.sortedByDescending { it.UrunAdi }
        binding.spFiltre.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                urunList?.let { initRecyleView(it) }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        urunList?.let { initRecyleView(it) }
    }


    fun loadingFinish(){
        loading.isDismiss()

    }

    fun initRecyleView(urunList: List<UrunItem>){
        if(binding.spFiltre.selectedItem.toString()==resources.getString(R.string.artan)){
            yeniUrunList=urunList.sortedBy { it.UrunAdi }?.take(2)
        }
        else{
            yeniUrunList=urunList.sortedByDescending { it.UrunAdi }?.take(2)
        }
        urunGridAdapter = UrunGridAdapter( yeniUrunList as ArrayList<UrunItem>, object :
            ItemClickListener {
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@ListingActivity, DetailActivity::class.java))
            }
        })

        binding.rcvUrunler.adapter = urunGridAdapter
        binding.rcvUrunler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.swcLayout.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                binding.rcvUrunler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            } else {
                binding.rcvUrunler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    fun urunleriAl() {
        urunViewHolder.apply {
            urunLiveData.observe(this@ListingActivity, Observer {

                loadingFinish()
                filter(it)
            })


            error.observe(this@ListingActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            })

            loading?.observe(this@ListingActivity, Observer {

            })

        }

    }
}