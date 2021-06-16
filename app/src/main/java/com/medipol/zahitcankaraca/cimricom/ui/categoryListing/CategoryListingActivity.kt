package com.medipol.zahitcankaraca.cimricom.ui.categoryListing

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.*
import com.medipol.zahitcankaraca.cimricom.R
import com.medipol.zahitcankaraca.cimricom.utils.ItemClickListener
import com.medipol.zahitcankaraca.cimricom.data.KategoriItem
import com.medipol.zahitcankaraca.cimricom.databinding.ActivityCategoryListingBinding
import com.medipol.zahitcankaraca.cimricom.utils.LoadingDialog
import com.medipol.zahitcankaraca.cimricom.ui.list.ListingActivity
import com.medipol.zahitcankaraca.cimricom.utils.AlertDialogUtil


class CategoryListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryListingBinding
    var kategoriViewModel =KategoriViewModel()
    private lateinit var kategoriAdapter: KategoriAdapter
    var categoryList: List<KategoriItem>? = null
    val loading = LoadingDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init(){
        loading.startLoading()
        kategorileriAl()

        binding.apply {
            binding.srcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    filtre(newText)
                    return false
                }
            })
        }
    }
    fun loadingFinish(){
        loading.isDismiss()

    }

    fun filtre(kategoriAdi :String?){
        kategoriAdi?.let {
            categoryList?.let {
                var filterCategory = it.filter { it.KategoriAdi!!.contains(kategoriAdi) }
                kategoriAdapter.setData(filterCategory)
                kategoriAdapter.notifyDataSetChanged()
            }

        }
    }

    fun initRecyleView(categoryList: List<KategoriItem>){
        kategoriAdapter= KategoriAdapter(categoryList as ArrayList<KategoriItem>,object :
            ItemClickListener {
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@CategoryListingActivity, ListingActivity::class.java))
            }
        })
        binding.rcvKategoriler.adapter = kategoriAdapter
        binding.rcvKategoriler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }


    fun kategorileriAl() {
        kategoriViewModel.apply {
            kategorilerLiveData.observe(this@CategoryListingActivity, Observer {
                loadingFinish()
                categoryList = it
                initRecyleView(it)

            })


            error.observe(this@CategoryListingActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@CategoryListingActivity, Observer {

            })

        }

    }

    override fun onBackPressed() {
        AlertDialogUtil.getAlertDialog(this@CategoryListingActivity, getString(R.string.alert_title),getString(R.string.alert_message2) , getString(R.string.alert_okey), getString(R.string.alert_no), getString(R.string.categoryActivity))
    }

}