package com.bassem.catdemo.ui.compose.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.repo.CatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: CatRepo) : ViewModel() {

    private var _breedsList = MutableStateFlow<Result?>(null)
    val breedsList: Flow<Result> get() = _breedsList.filterNotNull()

    init {
        fetchBreedsList()
    }

    private fun fetchBreedsList() = viewModelScope.launch {
        repo.getCatsBreeds().collect { result ->
            _breedsList.value = result

        }
    }

}