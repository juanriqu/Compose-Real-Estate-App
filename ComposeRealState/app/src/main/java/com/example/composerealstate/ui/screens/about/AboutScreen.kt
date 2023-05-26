package com.example.composerealstate.ui.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composerealstate.R
import com.example.composerealstate.common.Constants
import com.example.composerealstate.ui.common.components.MyTopAppBar
import com.example.composerealstate.ui.common.Screens
import com.example.composerealstate.ui.screens.about.components.HyperlinkText

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(
    bottomBar: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = Screens.About.title,
            )
        },
        bottomBar = {
            bottomBar()
        }
    ) {
        AboutScreenContent()
    }
}



@Composable
fun AboutScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.08f))
        Text(
            text = (Constants.ABOUT_DESCRIPTION),
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.08f))
        Text(text = Constants.DESIGN_AND_DEVELOPMENT)
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomEnd)
            ) {
                AsyncImage(
                    model = R.drawable.dtt_banner,
                    contentDescription = Constants.HOUSE_IMAGE_DESCRIPTION,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth(
                        0.4f
                    ),
                )
                HyperlinkText(
                    fullText = Constants.BY_DEVELOPER_NAME,
                    linkText = listOf(
                        Constants.DEVELOPER_NAME
                    ),
                    hyperlinks = listOf(
                        Constants.GITHUB_USER_URL
                    ),
                )
            }
        }
    }
}