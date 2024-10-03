package com.example.newsapp.presentation.settings

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.presentation.common.NewsTopBar
import com.example.newsapp.ui.theme.Green
import com.example.newsapp.utils.setAppLocale

@Composable
fun SettingsScreen(
    modifier : Modifier = Modifier
){
    val context = LocalContext.current
    val activity = (context as? Activity)
    Column(
        modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop)
    ){
        Text(
            text = stringResource(R.string.language),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val isExpanded = remember{
            mutableStateOf(false)
        }

        val itemIdx = remember {
            mutableIntStateOf(0)
        }

        val langs = listOf(
            stringResource(R.string.english),
            stringResource(R.string.arabic)
        )

        Box(
            modifier = Modifier.padding(horizontal = 32.dp).border(
                width = 1.dp,
                color = Green,
            ).clickable {
                isExpanded.value = true
            }
        ){
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp , horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(langs[itemIdx.intValue] , color = Green)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = Green
                )

            }

            DropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = {
                    isExpanded.value = false
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(langs[0])
                    },
                    onClick = {
                        itemIdx.intValue = 0
                        isExpanded.value = false

                        setAppLocale(context, "en")
                        activity?.let {
                            it.finish()
                            it.startActivity(Intent(context, it::class.java))
                        }
                    }
                )

                DropdownMenuItem(
                    text = {
                       Text(langs[1])
                    },
                    onClick = {
                        itemIdx.intValue = 1
                        isExpanded.value = false
                        setAppLocale(context, "ar")
                        activity?.let {
                            it.finish()
                            it.startActivity(Intent(context, it::class.java))
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSettingsScreen(){
    SettingsScreen()
}