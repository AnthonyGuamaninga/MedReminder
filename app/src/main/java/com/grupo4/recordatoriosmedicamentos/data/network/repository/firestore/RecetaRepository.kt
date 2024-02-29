package com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import kotlinx.coroutines.tasks.await

class RecetaRepository {

    private val db = Firebase.firestore

    suspend fun save(
        id:String,
        medicamentos:MutableList<String>,
        estado:Boolean,
        idUser:String,
        fecha_registro:String
    ) : Result<Receta> = runCatching{
        val rec =
            Receta(id, estado, medicamentos, idUser, fecha_registro)
        db.collection("Recetas").add(rec).await()
        return@runCatching rec
    }

    suspend fun getRecetaById(id:String) = runCatching {
        val receta = Receta(id,false)
        return@runCatching db.collection("Recetas")
            .document(receta.id)
            .get()
            .await<DocumentSnapshot?>()?.toObject<Receta>(Receta::class.java)
    }

    suspend fun getRecetaAll()=runCatching {
        return@runCatching db.collection("Recetas").get().await()
    }

    suspend fun getAllReceta():List<Receta>?{
        var documents = getRecetaAll().getOrNull()?.documents
        var list : MutableList<Receta> = ArrayList()
        if(documents != null){
            for (doc in documents){
                var temp = doc.toObject<Receta>(Receta::class.java)!!
                list.add(temp)
            }
        }
        return list.toList()
    }
}