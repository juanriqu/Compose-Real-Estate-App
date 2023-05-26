package com.example.composerealstate.ui.screens.listhouses

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composerealstate.common.Constants
import com.example.composerealstate.domain.model.House
import com.example.composerealstate.ui.common.Screens
import com.example.composerealstate.ui.common.components.MyTopAppBar
import com.example.composerealstate.ui.screens.listhouses.components.HouseCard
import com.example.composerealstate.ui.screens.listhouses.components.MySearchField
import com.example.composerealstate.ui.screens.listhouses.components.NoResultsFoundBanner
import com.example.composerealstate.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHousesScreen(
    viewModel: ListHousesViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
    bottomBar: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = Screens.ListHouses.title,
            )
        },
        bottomBar = {
            bottomBar()
        },
    ) { paddingValues ->
        val modifier = Modifier.padding(
            start = paddingValues.calculateStartPadding(
                LayoutDirection.Ltr
            ),
            bottom = paddingValues.calculateBottomPadding(),
            end = paddingValues.calculateEndPadding(
                LayoutDirection.Ltr
            ),
            top = paddingValues.calculateTopPadding()
        )

        LaunchedEffect(key1 = true) {
            viewModel.handleEvent(ListHousesContract.ListHousesEvents.LoadHouses)
        }
        val state = viewModel.uiStateFlow.collectAsStateWithLifecycle()

        state.value.error?.let {
            ErrorToast(message = it, onDismissError = {
                viewModel.handleEvent(
                    ListHousesContract.ListHousesEvents.OnErrorShown
                )
            })
        }

        state.value.isLoading.let {
            if (it) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        state.value.noResultsFound.let {
            if (it) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    NoResultsFoundBanner()
                }
            } else {
                HouseList(
                    onNavigateToDetail = onNavigateToDetail,
                    houses = state.value.houses,
                    modifier = modifier
                )
            }
        }
        MySearchField(
            query = state.value.searchQuery,
            onQueryChange = {
                viewModel.handleEvent(
                    ListHousesContract.ListHousesEvents.OnSearchQueryChange(
                        it
                    )
                )
            },
            modifier = modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun HouseList(
    onNavigateToDetail: (Int) -> Unit,
    houses: List<House>,
    modifier: Modifier,
) {
    val context = LocalContext.current
    Utils.calculateDistanceToHouses(houses, context)
    Column(
        modifier = modifier
            .padding(top = 64.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.925f)
                .fillMaxHeight()
        ) {
            items(houses.size) { index ->
                HouseCard(house = houses[index]) {
                    onNavigateToDetail(houses[index].id)
                }
            }
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



