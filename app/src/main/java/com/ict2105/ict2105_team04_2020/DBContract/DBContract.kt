package com.ict2105.ict2105_team04_2020.DBContract

import android.provider.BaseColumns

object DBContract{
    /* Inner class to define table content */
    class AccountEntry : BaseColumns{
        companion object{
            const val TABLE_NAME = "accounts"
            const val COLUMN_ACCOUNT_ID = "accountId" //PK
            const val COLUMN_USERNAME = "username"
            const val COLUMN_PASSWORD = "password"
            const val COLUMN_BIO = "bio"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_PHONE_NUMBER = "phoneNumber"
            const val COLUMN_RATING = "rating"
            const val COLUMN_PROFILE_IMG = "profileImg"
            const val COLUMN_USER_TYPE = "userType"
        }
    }

    class ListingEntry : BaseColumns{
        companion object{
            const val TABLE_NAME = "listings"
            const val COLUMN_LISTING_ID = "listingId" //PK
            const val COLUMN_ACCOUNT_ID = "accountId" //FK
            const val COLUMN_ITEM_NAME = "itemName"
            const val COLUMN_ITEM_DETAILS = "itemDetails"
            const val COLUMN_ITEM_CONDITION = "itemCondition"
            const val COLUMN_LOCATION = "location"
            const val COLUMN_CATEGORY = "category"
            const val COLUMN_ITEM_IMAGE1 = "itemImage1"
            const val COLUMN_ITEM_IMAGE2 = "itemImage2"
            const val COLUMN_ITEM_IMAGE3 = "itemImage3"
        }
    }

    class CharityOrganizationEntry : BaseColumns{
        companion object{
            const val TABLE_NAME = "charityOrganizations"
            const val COLUMN_ORGANIZATION_ID = "organizationId"
            const val COLUMN_ORGANIZATION_NAME = "organizationName"
            const val COLUMN_ORGANIZATION_ADDRESS = "organizationAddress"
            const val COLUMN_OPENING_HOURS = "openingHours"
            const val COLUMN_LONGITUDE = "longitude"
            const val COLUMN_LATITUDE = "latitude"
            const val COLUMN_ORGANIZATION_IMG = "organizationImg"
        }
    }

    class VolunteerRequestEntry: BaseColumns{
        companion object{
            const val TABLE_NAME = "volunteerRequests"
            const val COLUMN_REQUEST_ID = "requestId" //PK
            const val COLUMN_LISTING_ID = "listingId" //FK
            const val COLUMN_PICKUP_LOCATION = "pickupLocation"
        }
    }
}