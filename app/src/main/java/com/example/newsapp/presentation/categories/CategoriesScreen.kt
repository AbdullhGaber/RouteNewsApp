package com.example.newsapp.presentation.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.presentation.categories.components.NewsCategoryLazyVerticalGrid
import com.example.newsapp.presentation.common.NewsTopBar

@Composable
fun CategoriesScreen(
    onCategoryCardClick : (String,String) -> Unit = {q,n->}
){
    Column(
        modifier = Modifier
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.Crop
            )
            .fillMaxSize()
    ){
        NewsTopBar(title = stringResource(id = R.string.app_name),)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.choose_category_of_your_interest),
            fontSize = 24.sp,
            modifier = Modifier.align(CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        NewsCategoryLazyVerticalGrid(
            onCardClick = { queryName,name, ->
                onCategoryCardClick(queryName , name)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCategoriesScreen(){
    CategoriesScreen()
}