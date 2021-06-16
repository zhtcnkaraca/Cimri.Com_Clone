package com.medipol.zahitcankaraca.cimricom.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.medipol.zahitcankaraca.cimricom.data.UrunItem
import com.medipol.zahitcankaraca.cimricom.data.repository.UrunRepository
import com.medipol.zahitcankaraca.cimricom.utils.ResourceStatus
import kotlinx.coroutines.launch

class UrunViewHolder : ViewModel() {

    private  val urunRepository: UrunRepository = UrunRepository()

    init {
        urunleriGetir()
    }
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var urunLiveData = MutableLiveData<List<UrunItem>>()
    var error =    MutableLiveData<Throwable>()


    fun urunleriGetir()  = viewModelScope.launch {

        urunRepository.urunleriGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        urunLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}