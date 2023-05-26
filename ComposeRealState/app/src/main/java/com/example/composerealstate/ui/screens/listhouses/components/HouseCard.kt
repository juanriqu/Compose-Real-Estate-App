package com.example.composerealstate.ui.screens.listhouses.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composerealstate.common.Constants
import com.example.composerealstate.domain.model.House
import com.example.composerealstate.ui.common.components.HouseCardBasicDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseCard(
    house: House,
    onNavigateToDetail: (Int) -> Unit,
) {
    Card(
        onClick = {
            onNavigateToDetail(house.id)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            HouseCardImage(
                houseImageUrl = house.image
            )
            Spacer(
                modifier = Modifier.fillMaxWidth(0.05f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                HouseCardPrincipalDetails(
                    housePrice = house.price.toString(),
                    houseZipCode = house.zipCode,
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    HouseCardBasicDetail(
                        numberOfBedrooms = house.bedrooms.toString(),
                        numberOfBathrooms = house.bathrooms.toString(),
                        numberOfFloors = house.size.toString(),
                        locationDistance = house.distance.toString(),
                        color = Color.Gray,
                        spaceBetween = 0.05f,
                    )
                }
            }
        }
    }
}

@Composable
fun HouseCardImage(
    houseImageUrl: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .fillMaxHeight()
            .clip(
                RoundedCornerShape(8)
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = Constants.IMAGE_RELATIVE_PATH + houseImageUrl,
            ),
            contentDescription = Constants.HOUSE_IMAGE_DESCRIPTION,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Composable
fun HouseCardPrincipalDetails(
    housePrice: String,
    houseZipCode: String,
) {
    Text(
        text = "${Constants.DOLLAR_CHARACTER}${housePrice}",
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
    )
    Text(
        text = houseZipCode,
        color = Color.Gray,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
    )
}

@Preview
@Composable
fun HouseCardPreview() {
    HouseCard(
        house = House(
            id = 1,
            image = "https://images.unsplash.com/photo-1616489953148-8b8b8b5b5f1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
            price = 100000,
            bedrooms = 3,
            bathrooms = 2,
            size = 100,
            city = "New York",
            distance = "1000 meters",
            zipCode = "12345",
            latitude = 0,
            longitude = 0,
            description = "This is a house",
            creationDate = "2021-03-25",
        ),
        onNavigateToDetail = { }
    )
}