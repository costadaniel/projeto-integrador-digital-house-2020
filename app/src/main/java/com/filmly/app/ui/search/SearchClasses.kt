package com.filmly.app.ui.search

import com.filmly.app.data.model.Actor
import com.filmly.app.data.model.Film
import com.filmly.app.data.model.Serie
import kotlinx.serialization.Serializable

@Serializable
data class MovieResults(val page: Int = 1, val results: List<Movie>)

@Serializable
data class Movie(
    val poster_path: String? = null,
    val overview: String? = null,
    val id: Int?,
    val title: String? = null,
    val popularity: Double?,
    val release_date: String? = null,
    val genre_ids: List<Int>?,
    val vote_average: Double?
){
    fun convertToFilm(): Film {
        return Film(id = id, name = title, image = poster_path, descricao = overview, type = "movie", popularity)
    }
}

@Serializable
data class TvResults(val page: Int, val results: List<TvShow>)

@Serializable
data class TvShow(
    val poster_path: String? = null,
    val overview: String? = null,
    val id: Int?,
    val name: String? = null,
    val first_air_date: String? = null,
    val popularity: Double?,
    val genre_ids: List<Int>?,
    val vote_average: Double?
){
    fun convertToSerie(): Serie {
        return Serie(id = id, name = name, image = poster_path, descricao = overview, type = "tv", popularity)
    }
}

@Serializable
data class PersonResults(val page: Int, val results: List<Person>)

@Serializable
data class Person(
    val profile_path: String? = null,
    val id: Int?,
    val known_for: List<KnownFor>,
    val name: String? = null,
    val popularity: Double? = null
){
    fun convertToActor(): Actor {
        val over = known_for.map {
            it.title ?: it.name

        }
        return Actor(id = id, name = name, image = profile_path, descricao = null, type = "person", null, popularity)
    }
}

@Serializable
data class KnownFor(
    val title: String? = null,
    val poster_path: String? = null,
    val overview: String? = null,
    val media_type: String? = null,
    val id: Int,
    val name: String? = null,
    val first_air_date: String? = null,
    val popularity: Double? = null,
    val genre_ids: List<Double>?,
    val vote_average: Double?
) {
    fun convertToFilm() = Film(id, title, poster_path, overview, media_type, popularity)

    fun convertToSerie() = Serie(id, name, poster_path, overview, media_type, popularity)
}
