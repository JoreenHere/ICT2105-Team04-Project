package com.ict2105.ict2105_team04_2020.DBModel

class Listing{

    var listingId: Int = 0
    var accountId: Int = 0
    var username: String? = null
    var itemName: String? = null
    var itemCondition: String? = null
    var itemDetails: String? = null
    var location: String? = null
    var itemCategory: String? = null
    lateinit var itemImage1: ByteArray
    lateinit var itemImage2: ByteArray
    lateinit var itemImage3: ByteArray
    lateinit var profileImage: ByteArray

    constructor()

    constructor(accountId: Int, itemName: String, itemCondition: String, itemDetails: String, location: String, itemImage1: ByteArray, itemImage2: ByteArray, itemImage3: ByteArray){
        this.accountId = accountId
        this.itemName = itemName
        this.itemCondition = itemCondition
        this.itemDetails = itemDetails
        this.location = location
        this.itemImage1 = itemImage1
        this.itemImage2 = itemImage2
        this.itemImage3 = itemImage3
    }

    constructor(itemName: String){
        this.itemName = itemName
    }
}