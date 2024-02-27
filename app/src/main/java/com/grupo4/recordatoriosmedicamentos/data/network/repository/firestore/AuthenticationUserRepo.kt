package com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import kotlinx.coroutines.tasks.await



class AuthenticationUserRepo {
    private val auth = Firebase.auth
    suspend fun SingInUsers(email: String, password: String): Result<UserDB?> =
        runCatching {
            var userdb: UserDB? = null
            val us = auth.signInWithEmailAndPassword(email, password).await().user
            if (us != null) {
                userdb = UserDB(us.uid, us.email!!, us.displayName.orEmpty(), null)
            }
            return@runCatching userdb

        }

    suspend fun createNewUser(user: String, password: String, name: String): Result<UserDB?> =
        runCatching {
            var userdb: UserDB? = null
            val us = auth.createUserWithEmailAndPassword(
                user,
                password
            ).await().user
            if (us != null) {
                userdb = UserDB(us.uid, us.email!!, name, null)
            }

            return@runCatching userdb
        }

}