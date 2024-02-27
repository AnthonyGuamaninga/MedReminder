package com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import kotlinx.coroutines.tasks.await

class RecetaRepository {
    private val db = Firebase.firestore

    suspend fun saveRecetaDB(
        dosis: String,
        indicaciones: String,
        frecuencia: String,
        f_inicio: String,
        caducidad: String,
        observaciones: String,
        medId: String
    ): Result<Receta> = runCatching {
        var lista = getRecetaAll().getOrNull()?.documents
        Log.d("UCE4", lista?.size.toString())
        var id: String = ""
        if (lista!=null){
            id= lista.size.toString()+"_Receta"
        }else{
            id="0_Receta"
        }

        val us =
            Receta(id, dosis, indicaciones, frecuencia, f_inicio, caducidad, observaciones, medId)
        db.collection("Recetas").add(us).await()
        return@runCatching us
    }

    suspend fun getRecetaByID(id: String) = runCatching {
        val us = Receta(id, null, null, null, null, null, null, null)
        return@runCatching db.collection("Recetas")
            .document(us.id)
            .get()
            .await<DocumentSnapshot?>()?.toObject<Receta>(Receta::class.java)
    }

    suspend fun getRecetaAll() = runCatching {
        return@runCatching db.collection("Recetas")
            .get().await()

    }

    suspend fun getAllRecetas():List<Receta>?{
        var documents = getRecetaAll().getOrNull()?.documents
        var list: MutableList<Receta> = ArrayList()
        if (documents != null) {
            for (doc in documents){
                var temp =doc.toObject(Receta::class.java)!!
                temp.id=""
                list.add(temp)
            }
        }
        return list.toList()
    }
}