package com.example.composerealstate.ui.screens.detailedhouse

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.composerealstate.common.Constants
import com.example.composerealstate.domain.model.House
import com.example.composerealstate.ui.common.components.TopAppBarBack
import com.example.composerealstate.ui.screens.detailedhouse.components.DetailedHouseCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedHouseScreen(
    houseId: Int,
    onBack: () -> Unit,
    viewModel: DetailedHouseViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBarBack(
                onBack = onBack,
            )
        }
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.handleEvent(DetailedHouseContract.DetailedHouseEvent.GetHouse(houseId = houseId))
        }
        val state = viewModel.uiStateFlow.collectAsStateWithLifecycle()

        state.value.error?.let { error ->
            ErrorToast(
                message = error,
                onDismissError = {
                    viewModel.handleEvent(
                        DetailedHouseContract.DetailedHouseEvent.OnErrorShown
                    )
                }
            )
        }

        state.value.house.let { house ->
            HouseBox(
                house = house,
            )
        }
    }
}

@Composable
fun HouseBox(
    house: House,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = Constants.IMAGE_RELATIVE_PATH + house.image,
            contentDescription = Constants.HOUSE_IMAGE_DESCRIPTION,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
        ) {
            DetailedHouseCard(
                house = house,
            )
        }
    }
}


@Composable
fun ErrorToast(
    message: String,
    onDismissError: () -> Unit,
) {
    val context = LocalContext.current
    Toast.makeText(context, Constants.ERROR_TOAST_INDICATOR + message, Toast.LENGTH_SHORT).show()
    onDismissError()
}



