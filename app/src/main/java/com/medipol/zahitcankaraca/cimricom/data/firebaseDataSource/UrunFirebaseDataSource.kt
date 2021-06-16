package com.medipol.zahitcankaraca.cimricom.data.firebaseDataSource

import com.google.firebase.database.*
import com.medipol.zahitcankaraca.cimricom.utils.Datas
import com.medipol.zahitcankaraca.cimricom.data.remote.UrunDataSource
import com.medipol.zahitcankaraca.cimricom.data.UrunItem
import com.medipol.zahitcankaraca.cimricom.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UrunFirebaseDataSource: UrunDataSource {

    var urunListesi = arrayListOf<UrunItem>()
    override fun urunleriGetir(): Flow<Resource<List<UrunItem>>> = callbackFlow {
        try {
            offer(Resource.Loading())
            val myRef: Query = FirebaseDatabase.getInstance().getReference("Urunler").orderByChild("KategoriBilgisi").equalTo(Datas.kategoriBilgisi)

            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshoot in dataSnapshot.getChildren()) {
                        var item = postSnapshoot.getValue(UrunItem::class.java)!!
                        urunListesi.add(item)
                    }
                    offer(Resource.Success(urunListesi))
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            awaitClose {  }

        } catch (e: Exception) {
            offer(Resource.Error(e))
            e.printStackTrace()
        }
    }

}