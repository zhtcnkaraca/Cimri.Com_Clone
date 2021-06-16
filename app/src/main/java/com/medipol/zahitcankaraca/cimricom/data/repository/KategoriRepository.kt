package com.medipol.zahitcankaraca.cimricom.data.repository

import com.medipol.zahitcankaraca.cimricom.data.remote.KategoriDataSource
import com.medipol.zahitcankaraca.cimricom.data.firebaseDataSource.KategoriFirebaseDataSource
import com.medipol.zahitcankaraca.cimricom.data.KategoriItem
import com.medipol.zahitcankaraca.cimricom.utils.Resource
import kotlinx.coroutines.flow.Flow


class KategoriRepository {
    private var kategoriDataSource: KategoriDataSource?=null

    init {
        kategoriDataSource= KategoriFirebaseDataSource()
    }

    fun kategorileriGetir(): Flow<Resource<List<KategoriItem>>>
    {
        return kategoriDataSource!!.kategorileriGetir()
    }

}