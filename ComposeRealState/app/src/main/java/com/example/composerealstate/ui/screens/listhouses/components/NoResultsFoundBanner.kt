package com.example.composerealstate.ui.screens.listhouses.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composerealstate.R

@Composable
fun NoResultsFoundBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Image(
            painter = painterResource(id = R.drawable.search_state_empty),
            contentDescription = "No Results Found",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        Text(
            text = "No Results Found",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Perhaps try another search?",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(
                    top = 8.dp
                )
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(name = "NoResultsFoundBanner")
@Composable
private fun PreviewNoResultsFoundBanner() {
    NoResultsFoundBanner()
}