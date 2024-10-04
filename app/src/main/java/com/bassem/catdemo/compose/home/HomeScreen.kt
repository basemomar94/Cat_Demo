package com.bassem.catdemo.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.utils.Logger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val logger = Logger("HomeScreen")
    val breedsResult by viewModel.breedsList.collectAsState(initial = Result.Loading)
    logger.i("home screen result is $breedsResult")

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Cat Breeds") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            SearchBar(query = "", onQueryChange = {})

            when (breedsResult) {
                is Result.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is Result.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.default_padding))
                    ) {
                        val breeds = (breedsResult as Result.Success).breedItems
                        items(breeds) { item: BreedItem ->
                            BreedListItem(item, onClick = {})

                        }
                    }
                }

                is Result.Fail -> {
                    Text(
                        text = (breedsResult as Result.Fail).reasons,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                dimensionResource(id = R.dimen.default_padding)
                            )
                    )
                }
            }


        }

    }
}