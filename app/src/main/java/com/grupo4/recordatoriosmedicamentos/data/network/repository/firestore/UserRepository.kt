package com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = Firebase.firestore

    suspend fun saveUserDB(id: String, email:String, name: String, lastname:String, edad:Int):Result<UserDB> =runCatching{
        val us= UserDB(id, email,name,lastname, edad, null)
        db.collection("Users").add(us).await()
        return@runCatching us
    }

    suspend fun getDocUserByID(id:String)= runCatching{
        val us= UserDB(id, "")
        Log.d("UCE12",us.id)
        return@runCatching db.collection("Users")
            .document(us.id)
            .get()
            .await()
    }

    suspend fun getUserByID(id:String): UserDB? {
        return getDocUserByID(id).getOrNull()?.toObject<UserDB>(UserDB::class.java)
    }
}