package com.bassem.catdemo.ui.compose.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.repo.DetailsRep
import com.bassem.catdemo.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

const val BREED_ID = "breedId"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailsRepo: DetailsRep,
) : ViewModel() {
    private val logger = Logger(this::class.java.simpleName)

    private var _breed = MutableStateFlow<BreedItem?>(null)
    val breed: Flow<BreedItem> get() = _breed.filterNotNull()
    val id = savedStateHandle.get<Int>(BREED_ID) ?: -1


    fun getBreedById() = viewModelScope.launch {
        _breed.value = detailsRepo.getBreedById(id)
    }

    fun updateFavoriteStatus(id: String, isFavorite: Boolean) = viewModelScope.launch {
        detailsRepo.updateFavoriteStatus(id, isFavorite)
    }
}

