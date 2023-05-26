package com.example.composerealstate.ui.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composerealstate.R

@Composable
fun HouseCardBasicDetail(
    numberOfBedrooms: String,
    numberOfBathrooms: String,
    numberOfFloors: String,
    locationDistance: String,
    color: Color,
    spaceBetween: Float,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_bed),
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(14.dp)
    )
    Spacer(modifier = Modifier.fillMaxWidth(0.025f))
    Text(
        text = numberOfBedrooms,
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.fillMaxWidth(spaceBetween))
    Icon(
        painter = painterResource(id = R.drawable.ic_bath),
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(14.dp)
    )
    Spacer(modifier = Modifier.fillMaxWidth(0.025f))
    Text(
        text = numberOfBathrooms,
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.fillMaxWidth(spaceBetween))
    Icon(
        painter = painterResource(id = R.drawable.ic_layers),
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(14.dp)
    )
    Spacer(modifier = Modifier.fillMaxWidth(0.025f))
    Text(
        text = numberOfFloors,
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.fillMaxWidth(spaceBetween))
    Icon(
        painter = painterResource(id = R.drawable.ic_location),
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(14.dp)
    )
    Spacer(modifier = Modifier.fillMaxWidth(0.025f))
    Text(
        text = locationDistance,
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.fillMaxWidth(spaceBetween))
}

@Composable
@Preview
fun HouseCardBasicDetailPreview() {
    HouseCardBasicDetail(
        numberOfBedrooms = "3",
        numberOfBathrooms = "2",
        numberOfFloors = "1",
        locationDistance = "1.5 mi",
        color = Color.Black,
        spaceBetween = 0.05f,
    )
}