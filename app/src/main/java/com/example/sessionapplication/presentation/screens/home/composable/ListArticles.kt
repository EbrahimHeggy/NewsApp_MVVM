package com.example.sessionapplication.presentation.screens.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sessionapplication.data.reomte.model.news.model.Article
import com.example.sessionapplication.presentation.screens.home.HomeViewModel

@Composable
fun ListArticles(list: List<Article>, text: String , isDependOnArticleFlag :Boolean=false,onClick: (Article, Int) -> Unit ) {

    val deletedArticleIndices = remember { mutableStateListOf<Int>() }

    if (list.isNullOrEmpty()) return
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        itemsIndexed(list) {index,it->
            val insertionState = remember { mutableStateOf(it.isInsert) }
            val isArticleDeleted = index in deletedArticleIndices
            //val buttonState = remember { mutableStateOf(isInsertState.value) }

            ElevatedCard(
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),

                ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "title : ", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        Text(text = it?.title?:"" , fontSize = 17.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Author : ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = it?.author?:"" , fontSize = 17.sp)
                    }
                    Text(
                        text = "Description : ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = it?.description?:"",
                        fontSize = 17.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,

                        )
                    Button(
                        onClick = {
                            onClick(it,index)
                            insertionState.value = !insertionState.value
                            if (isArticleDeleted) {
                                deletedArticleIndices.remove(index)
                            }
                                  },
                        enabled = if(isDependOnArticleFlag) !insertionState.value && !isArticleDeleted  else true,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        //Text(text = if (!it.isInsert) "Insert" else "Delete")
                        Text(text = text)
                    }

                }


            }
        }
    }
}
