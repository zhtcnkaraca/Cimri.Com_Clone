package com.medipol.zahitcankaraca.cimricom.data.repository

import com.medipol.zahitcankaraca.cimricom.data.remote.UrunDataSource
import com.medipol.zahitcankaraca.cimricom.data.firebaseDataSource.UrunFirebaseDataSource
import com.medipol.zahitcankaraca.cimricom.data.UrunItem
import com.medipol.zahitcankaraca.cimricom.utils.Resource
import kotlinx.coroutines.flow.Flow

class UrunRepository {
    private var urunDataSource: UrunDataSource?=null

    init {
        urunDataSource= UrunFirebaseDataSource()
    }

    fun urunleriGetir(): Flow<Resource<List<UrunItem>>>
    {
        return urunDataSource!!.urunleriGetir()
    }
}