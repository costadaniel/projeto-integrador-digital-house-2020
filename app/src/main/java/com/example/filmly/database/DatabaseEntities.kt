package com.example.filmly.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val room_id: Int? = null,
    val marvel_id: Int?,
    val name: String?,
    val image: String?,
    val description: String?
)

@Entity(tableName = "favorite_series")
data class Serie(
    @PrimaryKey(autoGenerate = true)
    val room_id: Int? = null,
    val marvel_id: Int?,
    val name: String?,
    val image: String?,
    val description: String?
)

@Entity(tableName = "favorite_actors")
data class Actor(
    @PrimaryKey(autoGenerate = true)
    val room_id: Int? = null,
    val marvel_id: Int?,
    val name: String?,
    val image: String?,
    val description: String?
)

@Entity(tableName = "watcheds")
data class Watched(
    @PrimaryKey(autoGenerate = true)
    val room_id: Int? = null,
    val marvel_id: Int?,
    val name: String?,
    val image: String?,
    val description: String?
)

fun List<Film>.asFilmDomain(): List<com.example.filmly.data.model.Film> {
    return this.map {
        com.example.filmly.data.model.Film(id = it.marvel_id, name = it.name, image = it.image, descricao = it.description)
    }
}

fun List<Serie>.asSerieDomain(): List<com.example.filmly.data.model.Serie> {
    return this.map {
        com.example.filmly.data.model.Serie(id = it.marvel_id, name = it.name, image = it.image, descricao = it.description)
    }
}

fun List<Actor>.asActorDomain(): List<com.example.filmly.data.model.Actor> {
    return this.map {
        com.example.filmly.data.model.Actor(id = it.marvel_id, name = it.name, image = it.image, descricao = it.description)
    }
}