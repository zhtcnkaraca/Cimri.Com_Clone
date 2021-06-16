package com.medipol.zahitcankaraca.cimricom.data.remote

import com.medipol.zahitcankaraca.cimricom.data.UrunItem
import com.medipol.zahitcankaraca.cimricom.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UrunDataSource {
    fun urunleriGetir(): Flow<Resource<List<UrunItem>>>
}