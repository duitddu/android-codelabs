package com.duitddu.android.codelabs.compose

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.duitddu.android.codelabs.compose.repository.FakeMyRepositoryImpl
import com.duitddu.android.codelabs.compose.ui.AndroidComposeTheme
import com.duitddu.android.codelabs.compose.viewmodel.MainViewModel
import com.duitddu.android.codelabs.compose.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(FakeMyRepositoryImpl()))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTheme {
                val myModels = viewModel.myModels.observeAsState().value ?: emptyList()
                val clickedModel = viewModel.onItemClickEvent.observeAsState().value

                MyModelList(models = myModels, onItemClick = viewModel::onItemClick)

                if (clickedModel != null) {
                    ComposableToast(clickedModel.title)
                }
            }
        }

        viewModel.loadMyModels()
    }
}

@Composable
fun MyModelList(models: List<MyModel>, onItemClick: (Int) -> Unit = {}) {
    LazyColumnForIndexed(
        items = models,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) { index, item ->
        MyModelListItem(model = item, onClick = {
            onItemClick.invoke(index)
        })
    }
}

@Composable
fun MyModelListItem(model: MyModel, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        backgroundColor = Color.White,
        contentColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Text(
            text = model.title,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun ComposableToast(message: String) {
    Toast.makeText(ContextAmbient.current, message, Toast.LENGTH_SHORT).show()
}