package com.bassem.catdemo.ui.compose.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.repo.FavoriteRepo
import com.bassem.catdemo.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepo: FavoriteRepo) : ViewModel() {

    private val logger = Logger(this::class.java.simpleName)
    private var _favoritesList = MutableStateFlow<Result<Any?>?>(null)
    val favoritesList: Flow<Result<Any?>> get() = _favoritesList.filterNotNull()

    fun fetchFavorites() {
        viewModelScope.launch {
            _favoritesList.value = try {
                val favorites = favoriteRepo.getFavorites()
                logger.i("successfully got favorites ${favorites.size}")
                Result.Success(favorites)
            } catch (e: Exception) {
                logger.e("failed getting favorites ${e.message}")
                Result.Fail(e.message ?: "Unknown Error")
            }
        }
    }

    fun removeFavorite(id: String) = viewModelScope.launch {
        favoriteRepo.removeFavorite(id)

    }
}