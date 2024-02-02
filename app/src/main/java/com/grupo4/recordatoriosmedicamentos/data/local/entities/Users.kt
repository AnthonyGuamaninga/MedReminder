package com.grupo4.recordatoriosmedicamentos.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users (
    val userName: String? = null,
    val password: String? = null
)
{
    @PrimaryKey(autoGenerate = true)
    var userId = -1
    var firstName : String = " "
    var lastName : String = " "
    var profile : String = " "

    constructor(
        username: String?,
        password: String?, userId: Int
    ) : this(username, password)
    {
        this.userId = userId
    }

    constructor(
        id: Int, username: String?,
        password: String?, profile: String
    ) : this(username, password)
    {
        this.profile = profile
    }

    constructor( userName:String?, password:String?,userId:Int, firstName:String, lastName:String)
            :this(userName,password){
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
    }

}