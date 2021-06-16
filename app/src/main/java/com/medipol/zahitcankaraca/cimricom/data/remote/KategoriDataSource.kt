package com.medipol.zahitcankaraca.cimricom.data.remote

import com.medipol.zahitcankaraca.cimricom.data.KategoriItem
import com.medipol.zahitcankaraca.cimricom.utils.Resource
import kotlinx.coroutines.flow.Flow


interface KategoriDataSource {
    fun kategorileriGetir(): Flow<Resource<List<KategoriItem>>>
}