package com.example.composerealstate.ui.screens.listhouses.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchField(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        placeholder = {
            Text(text = "Search here")
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear search query",
                    modifier = Modifier.clickable {
                        onQueryChange("")
                    }
                )
            }
        },
        modifier = modifier
            .fillMaxHeight(0.085f)
            .fillMaxWidth(),
    )
}

@Preview(name = "MySearchField")
@Composable
private fun PreviewMySearchField() {
    MySearchField()
}