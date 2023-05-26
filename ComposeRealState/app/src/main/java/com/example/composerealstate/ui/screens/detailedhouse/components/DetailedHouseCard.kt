package com.example.composerealstate.ui.screens.detailedhouse.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.composerealstate.common.Constants
import com.example.composerealstate.domain.model.House
import com.example.composerealstate.ui.common.components.HouseCardBasicDetail
import com.example.composerealstate.utils.Utils
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun DetailedHouseCard(
    house: House,
) {
    val context = LocalContext.current
    Utils.getHouseWithDistance(myObject = house, context = context)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            PrincipalHouseDetails(
                house = house
            )
            DescriptionHouseDetails(
                house = house
            )
            LocationHouseDetails(
                house = house
            )
        }
    }
}

@Composable
fun PrincipalHouseDetails(
    house: House,
) {
    val spaceBetween = 0.1f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(
                0.05f
            )
            .padding(
                top = 0.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 0.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(alignment = Alignment.CenterStart)
        ) {
            Text(
                text = Constants.DOLLAR_CHARACTER + house.price.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.25f))
            HouseCardBasicDetail(
                numberOfBedrooms = house.bedrooms.toString(),
                numberOfBathrooms = house.bathrooms.toString(),
                numberOfFloors = house.size.toString(),
                locationDistance = house.distance.toString(),
                color = Color.Black,
                spaceBetween = spaceBetween
            )
        }
    }
}

@Composable
fun DescriptionHouseDetails(
    house: House,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(0)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(
                0.4f
            )
            .padding(
                top = 0.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 0.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Text(
                text = "Description",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(
                    alignment = Alignment.Start
                )
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.04f))
            Text(
                text = (house.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(scroll)
                    .align(alignment = Alignment.Start)
            )
        }
    }
}

@Composable
fun LocationHouseDetails(
    house: House,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                top = 0.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Text(
                text = "Location",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(
                    alignment = Alignment.Start
                )
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Map(
                house = house
            )
        }
    }
}

@Composable
fun Map(
    house: House,
    context: Context = LocalContext.current
) {
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        cameraPositionState = CameraPositionState(
            CameraPosition(
                LatLng(house.latitude.toDouble(), house.longitude.toDouble()), 17f, 0f, 0f
            )
        ), onMapClick = {
            val url = "${Constants.GOOGLE_MAPS_BASE_URL}${house.latitude},${house.longitude}"
            Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    url
                )
            ).also {
                it.setPackage(Constants.GOOGLE_MAPS_PACKAGE_NAME)
                ContextCompat.startActivity(
                    context, it, null
                )
            }
        }, uiSettings = MapUiSettings(
            compassEnabled = false,
            indoorLevelPickerEnabled = false,
            mapToolbarEnabled = false,
            myLocationButtonEnabled = false,
            rotationGesturesEnabled = false,
            scrollGesturesEnabled = false,
            scrollGesturesEnabledDuringRotateOrZoom = false,
            tiltGesturesEnabled = false,
            zoomControlsEnabled = true,
            zoomGesturesEnabled = true
        )
    ) {
        Marker(
            state = MarkerState(
                LatLng(house.latitude.toDouble(), house.longitude.toDouble())
            ), title = Constants.MARKER_NAME
        )
    }
}