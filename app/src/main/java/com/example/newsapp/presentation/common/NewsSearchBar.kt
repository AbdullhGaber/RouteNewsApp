package com.example.newsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSearchBar(
    modifier: Modifier = Modifier,
    query : MutableState<String> = mutableStateOf(""),
    onSearch : (String) -> Unit = {},
    onCloseIconClick : () -> Unit = {},
    active : Boolean = false,
){

    val active = remember {
        mutableStateOf(active)
    }

    SearchBar(
        query = query.value,
        onQueryChange = {
            query.value = it
        },
        onSearch = onSearch,
        active = false,
        onActiveChange = {
            active.value = it
        },
        placeholder = {
            Text(text = stringResource(R.string.search_for_articles))
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Green,
                modifier = Modifier.size(24.dp)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = Green,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if (query.value.isEmpty()) {
                            onCloseIconClick()
                            active.value = false
                        } else {
                            query.value = ""
                        }
                    }
            )
        },
        modifier = modifier.fillMaxWidth(),
        colors = SearchBarDefaults.colors(
            containerColor = Color.White
        )
    ) {

    }
}

@Composable
@Preview
fun PreviewSearchBar(){
    NewsSearchBar()
}