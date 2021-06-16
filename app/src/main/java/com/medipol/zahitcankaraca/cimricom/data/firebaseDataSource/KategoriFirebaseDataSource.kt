package com.medipol.zahitcankaraca.cimricom.data.firebaseDataSource

import com.google.firebase.database.*
import com.medipol.zahitcankaraca.cimricom.data.remote.KategoriDataSource
import com.medipol.zahitcankaraca.cimricom.data.KategoriItem
import com.medipol.zahitcankaraca.cimricom.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class KategoriFirebaseDataSource: KategoriDataSource {


    var kategoriListesi = arrayListOf<KategoriItem>()
    override  fun kategorileriGetir(): Flow<Resource<List<KategoriItem>>> = callbackFlow {
        try {
            offer(Resource.Loading())
            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Kategoriler")
            val myRef: DatabaseReference = database

            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshoot in dataSnapshot.getChildren()) {
                        var item = postSnapshoot.getValue(KategoriItem::class.java)!!
                        kategoriListesi.add(item)
                    }
                    offer(Resource.Success(kategoriListesi))
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            awaitClose {  }

        } catch (e: Exception) {
            offer(Resource.Error(e))
            e.printStackTrace()
        }
    }

}
