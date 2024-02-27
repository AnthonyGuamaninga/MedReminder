package com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = Firebase.firestore

    suspend fun saveUserDB(id: String, email:String, name: String):Result<UserDB> =runCatching{
        val us= UserDB(id, email,name,null)
        db.collection("Users").add(us).await()
        return@runCatching us
    }

    suspend fun getUserByID(id:String)= runCatching{
        val us= UserDB(id, "","", null)
        return@runCatching db.collection("Users")
            .document(us.id)
            .get()
            .await<DocumentSnapshot?>()?.toObject<UserDB>(UserDB::class.java)
    }
}