package com.app.fintechlab.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fintechlab.domain.repositories.FilmsRepository
import com.app.fintechlab.presentation.entities.FilmCard
import com.app.fintechlab.presentation.entities.FilmPoster
import com.app.fintechlab.presentation.entities.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class FilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _filmCardsTop = MutableLiveData<List<FilmCard>>(listOf())
    val filmCardsTop: LiveData<List<FilmCard>> = _filmCardsTop

    private val _filmCardsFavourite = MutableLiveData<List<FilmCard>>(listOf())
    val filmCardsFavourite: LiveData<List<FilmCard>> = _filmCardsFavourite

    private val _filmPoster = MutableLiveData<FilmPoster>()
    val filmPoster: LiveData<FilmPoster> = _filmPoster

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    init {
        getFilmCards()
    }

    private fun getFilmCards() {
        _status.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                filmsRepository.refreshTopFilms()
                filmsRepository.refreshFavouriteFilms()
                _filmCardsTop.postValue(filmsRepository.getTopFilmCards())
                _filmCardsFavourite.postValue(filmsRepository.getFavouriteFilmCards())
                _status.postValue(Status.DONE)
            } catch (e: IOException) {
                _status.postValue(Status.ERROR)
                _filmCardsTop.postValue(listOf())
            }
        }
    }

    fun refreshFilmPoster(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            filmsRepository.refreshFilmPoster(id)
            _filmPoster.postValue(filmsRepository.getFilmPoster())
        }
    }

    fun clearFilmPoster() {
        viewModelScope.launch(Dispatchers.IO) {
            _filmPoster.postValue(FilmPoster("", "", "", "", ""))
        }
    }

    fun addFavouriteFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            filmsRepository.addFavouriteFilm(id)
            filmsRepository.refreshFavouriteFilms()
            _filmCardsFavourite.postValue(filmsRepository.getFavouriteFilmCards())
        }
    }

}