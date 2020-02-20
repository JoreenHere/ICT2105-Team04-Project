package com.ict2105.ict2105_team04_2020.DBModel

import java.sql.Blob

class Account{
    var accountId: Int = 0
    var username: String? = null
    var password: String? = null
    var bio: String? = null
    var email: String? = null
    var phoneNumber: String? = null
    var rating: Float = 0.0f
    var userType: String? = null
    lateinit var profileImage: ByteArray

    constructor()

    constructor(accountId: Int, username: String, password: String){
        this.accountId = accountId
        this.username = username
        this.password = password
    }

    constructor(username: String, bio: String, email: String, phoneNumber: String, rating: Float, profileImage: ByteArray){
        this.username = username
        this.bio = bio
        this.email = email
        this.phoneNumber = phoneNumber
        this.rating = rating
        this.profileImage = profileImage
    }

}