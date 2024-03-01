package com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import kotlinx.coroutines.tasks.await

class MedicamentoRepository {
    private val db = Firebase.firestore

    suspend fun saveMediDB(
        idReceta: String,
        dosis: String,
        frecuencia: String,
        f_inicio: String,
        hora_inicio:String,
        caducidad: String,
        indicaciones: String,
        observaciones: String,
        medId: String
    ): Result<MedInfo> = runCatching {
        val med =
            MedInfo(idReceta, dosis, frecuencia, f_inicio, hora_inicio, caducidad, indicaciones, observaciones, medId)
        db.collection("Medicamentos").document(idReceta).set(med).await()
        return@runCatching med
    }

    suspend fun getMediByID(id: String) = runCatching {
        val med = MedInfo(id)
        return@runCatching db.collection("Medicamentos")
            .document(med.id)
            .get()
            .await<DocumentSnapshot?>()?.toObject<MedInfo>(MedInfo::class.java)
    }

    suspend fun getAllMediByID(id: String) = runCatching {
        val med = MedInfo(id)
        return@runCatching db.collection("Medicamentos")
            .whereGreaterThanOrEqualTo("id", id).whereLessThanOrEqualTo("id", id + "\uf8ff").get()
            .await().documents
    }

    suspend fun getMediAll() = runCatching {
        return@runCatching db.collection("Medicamentos")
            .get().await()
    }

    suspend fun updateMedByID(medicamento: MedInfo)= runCatching {
        val med = getMediByID(medicamento.id).getOrNull()
        if (med!=null){
            return@runCatching db.collection("Medicamentos")
                .document(med.id).set(medicamento)
        }
        return@runCatching null
    }

    suspend fun getAllMedi():List<MedInfo>?{
        var documents = getMediAll().getOrNull()?.documents
        var list: MutableList<MedInfo> = ArrayList()
        if (documents != null) {
            for (doc in documents){
                var temp =doc.toObject(MedInfo::class.java)!!
                list.add(temp)
            }
        }
        return list.toList()
    }
}