package com.example.filmly.network

import androidx.paging.PagingSource
import com.example.filmly.ui.home.HomeActorNetwork
import com.example.filmly.ui.home.HomeFilmNetwork
import com.example.filmly.ui.home.HomeSerieNetwork
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val service: TmdbApi
) : PagingSource<Int, HomeFilmNetwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeFilmNetwork> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getAllPopularMovies(position)
            val movies = response.results.map { it }
            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}

class TVPagingSource(
    private val service: TmdbApi
) : PagingSource<Int, HomeSerieNetwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeSerieNetwork> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getAllPopularTV(position)
            val series = response.results
            val nextKey = if (series.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = series,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}

class ActorsPagingSource(
    private val service: TmdbApi
) : PagingSource<Int, HomeActorNetwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeActorNetwork> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getAllPopularActors(position)
            val actors = response.results
            val nextKey = if (actors.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = actors,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}