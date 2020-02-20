package com.ict2105.ict2105_team04_2020.DBModel

class CharityOrganization{
    var organizationId: Int = 0
    var organizationName: String? = null
    var organizationAddress: String? = null
    var openingHours: String? = null
    var longitude: String? = null
    var latitude: String? = null
    lateinit var organizationImage: ByteArray

    constructor()
}