package com.example.composerealstate.common

object Constants {
    //API Constants
    const val BASE_URL = "https://1intern.d-tt.nl/api/"
    const val API_KEY = "98bww4ezuzfePCYFxJEWyszbUXc7dxRx"
    const val IMAGE_RELATIVE_PATH = "https://intern.d-tt.nl/"

    //Screens Routes Constants
    const val LIST_HOUSES_SCREENS_ROUTE = "list_houses"
    const val DETAILED_HOUSE_SCREEN_ROUTE = "detail_house"
    const val ABOUT_SCREEN_ROUTE = "about"

    //Screens Titles Constants
    const val LIST_HOUSES_Screens_TITLE = "DTT REAL ESTATE"
    const val DETAILED_HOUSE_SCREEN_TITLE = "DETAIL HOUSE"
    const val ABOUT_SCREEN_TITLE = "ABOUT"

    //Argument Constants
    const val ARG_DETAILED_HOUSE_SCREEN_HOUSE_ID = "houseId"
    const val DETAILED_HOUSE_SCREEN_ROUTE_WITH_ARGS = "detail_house/{houseId}"

    //Google Maps Constants
    const val GOOGLE_MAPS_PACKAGE_NAME = "com.google.android.apps.maps"
    const val GOOGLE_MAPS_BASE_URL = "https://www.google.com/maps/search/?api=1&query="

    //Descriptions Constants
    const val BACK_BUTTON_DESCRIPTION = "Back"
    const val HOUSE_IMAGE_DESCRIPTION = "House Image"
    const val NAVIGATION_ICON_DESCRIPTION = "Navigation Icon"

    //Detailed House Screen Constants
    const val MARKER_NAME = "House"

    //House Constants
    const val HOUSE_ID_COLUMN = "id"
    const val HOUSE_IMAGE_COLUMN = "image"
    const val HOUSE_PRICE_COLUMN = "price"
    const val HOUSE_BEDROOMS_COLUMN = "bedrooms"
    const val HOUSE_BATHROOMS_COLUMN = "bathrooms"
    const val HOUSE_SIZE_COLUMN = "size"
    const val HOUSE_DESCRIPTION_COLUMN = "description"
    const val HOUSE_ZIP_COLUMN = "zip"
    const val HOUSE_CITY_COLUMN = "city"
    const val HOUSE_LATITUDE_COLUMN = "latitude"
    const val HOUSE_LONGITUDE_COLUMN = "longitude"
    const val HOUSE_CREATION_DATE_COLUMN = "createdDate"

    //DatabaseModule Constants
    const val DB_PATH = "database/houses.db"
    const val ASSET_DB = "assetDB"
    const val HOUSES_DATABASE = "houses_database"

    //AuthInterceptor Constants
    const val API_KEY_HEADER = "Access-Key"

    //Fragments Titles
    const val LIST_HOUSES_FRAGMENT_TITLE = "DTT REAL ESTATE"

    //About Screen Constants
    const val ABOUT_DESCRIPTION =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec mauris vel dui sodales accumsan. Nulla facilisi. Nulla facilisi. Phasellus et velit at mauris vestibulum ultricies. Curabitur vitae nisi et nunc lacinia bibendum. Quisque at quam ut risus luctus sollicitudin. Quisque eget semper mauris. Nunc aliquet, orci a luctus pharetra, elit elit faucibus augue, nec ultricies est nisl in tellus."

    const val DESIGN_AND_DEVELOPMENT = "Design and Development"
    const val BY_DEVELOPER_NAME = "by Juan Riquelme"
    const val DEVELOPER_NAME = "Juan Riquelme"
    const val GITHUB_USER_URL = "https://www.github.com/juanriqu"
    const val URL = "URL"


    //House Details Constants
    const val DOLLAR_CHARACTER = "$"
    const val NO_DISTANCE_AVAILABLE = "N/A"


    //Query Constants
    const val QUERY_GET_ALL_HOUSES_PRICE_ASC = "SELECT * FROM house order by price ASC"
    const val QUERY_GET_HOUSE_BY_ID = "SELECT * FROM house WHERE id = :id"

    //Errors Constants
    const val ERROR_TOAST_INDICATOR = "Error: "
    const val NO_INTERNET_CONNECTION_ERROR = "No internet connection"
}