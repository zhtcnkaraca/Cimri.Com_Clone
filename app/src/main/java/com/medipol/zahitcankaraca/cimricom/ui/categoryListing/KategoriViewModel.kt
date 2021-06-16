package com.medipol.zahitcankaraca.cimricom.ui.categoryListing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.medipol.zahitcankaraca.cimricom.data.KategoriItem
import com.medipol.zahitcankaraca.cimricom.data.repository.KategoriRepository
import com.medipol.zahitcankaraca.cimricom.utils.ResourceStatus

import kotlinx.coroutines.launch

class KategoriViewModel : ViewModel() {

    private  val kategoriRepository: KategoriRepository = KategoriRepository()

    init {
        kategorileriGetir()
    }
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var kategorilerLiveData = MutableLiveData<List<KategoriItem>>()
    var error =    MutableLiveData<Throwable>()


    fun kategorileriGetir()  = viewModelScope.launch {

        kategoriRepository.kategorileriGetir()

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            kategorilerLiveData.postValue(it.data!!)
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

