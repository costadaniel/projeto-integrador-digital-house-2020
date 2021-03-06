package com.filmly.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.filmly.app.R
import com.filmly.app.data.model.Card
import com.filmly.app.data.model.CardDetail
import com.filmly.app.ui.home.HomeActorNetwork
import com.filmly.app.ui.home.HomeFilmNetwork
import com.filmly.app.ui.home.HomeSerieNetwork
import com.filmly.app.utils.CardDetailNavigation
import com.filmly.app.utils.CardDiffCallback
import kotlinx.android.synthetic.main.cards_list_item.view.*
import kotlinx.android.synthetic.main.item_mini_known_for_cards.view.*

class PopularMoviesAdapter(
    val cardInfo: Int,
    val cardNavigation: CardDetailNavigation,
    val miniCards: Boolean = false
) : PagingDataAdapter<HomeFilmNetwork, PopularMoviesAdapter.PopularMoviesViewHolder>(
    POPULAR_MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder.from(parent, miniCards = miniCards)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, cardInfo, cardNavigation, miniCards)
        }
    }

    companion object {
        private val POPULAR_MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<HomeFilmNetwork>() {
            override fun areItemsTheSame(oldItem: HomeFilmNetwork, newItem: HomeFilmNetwork): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomeFilmNetwork, newItem: HomeFilmNetwork): Boolean =
                oldItem == newItem
        }
    }

    class PopularMoviesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: HomeFilmNetwork, cardInfo: Int, cardNavigation: CardDetailNavigation, miniCards: Boolean) {
            val imageView = if (!miniCards) view.iv_cardImage else view.iv_known_for

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.setColorSchemeColors(
                view.resources.getColor(R.color.color_main)
            )
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(view).asBitmap()
                .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .into(imageView)

            val cardDetail = CardDetail(cardInfo, item.convertToFilm())
            imageView.setOnClickListener {
                cardNavigation.onClick(cardDetail)
            }
        }

        companion object {
            fun from(parent: ViewGroup, miniCards: Boolean = false): PopularMoviesViewHolder {
                val layout = if (!miniCards) R.layout.cards_list_item else R.layout.item_mini_known_for_cards

                val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
                return PopularMoviesViewHolder(view)
            }
        }
    }
}

class PopularTVAdapter(
    val cardInfo: Int,
    val cardNavigation: CardDetailNavigation,
    val miniCards: Boolean = false
) : PagingDataAdapter<HomeSerieNetwork, PopularTVAdapter.PopularTVViewHolder>(
    POPULAR_TV_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTVViewHolder {
        return PopularTVViewHolder.from(parent, miniCards = miniCards)
    }

    override fun onBindViewHolder(holder: PopularTVViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, cardInfo, cardNavigation, miniCards)
        }
    }

    companion object {
        private val POPULAR_TV_COMPARATOR = object : DiffUtil.ItemCallback<HomeSerieNetwork>() {
            override fun areItemsTheSame(oldItem: HomeSerieNetwork, newItem: HomeSerieNetwork): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomeSerieNetwork, newItem: HomeSerieNetwork): Boolean =
                oldItem == newItem
        }
    }

    class PopularTVViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: HomeSerieNetwork, cardInfo: Int, cardNavigation: CardDetailNavigation, miniCards: Boolean) {
            val imageView = if (!miniCards) view.iv_cardImage else view.iv_known_for

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.setColorSchemeColors(
                view.resources.getColor(R.color.color_main)
            )
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(view).asBitmap()
                .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .into(imageView)

            val cardDetail = CardDetail(cardInfo, item.convertToSerie())
            imageView.setOnClickListener {
                cardNavigation.onClick(cardDetail)
            }
        }

        companion object {
            fun from(parent: ViewGroup, miniCards: Boolean = false): PopularTVViewHolder {
                val layout = if (!miniCards) R.layout.cards_list_item else R.layout.item_mini_known_for_cards

                val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
                return PopularTVViewHolder(view)
            }
        }
    }
}

class PopularActorsAdapter(
    val cardInfo: Int,
    val cardNavigation: CardDetailNavigation,
    val miniCards: Boolean = false
) : PagingDataAdapter<HomeActorNetwork, PopularActorsAdapter.PopularActorsViewHolder>(
    POPULAR_ACTOR_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularActorsViewHolder {
        return PopularActorsViewHolder.from(parent, miniCards = miniCards)
    }

    override fun onBindViewHolder(holder: PopularActorsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, cardInfo, cardNavigation, miniCards)
        }
    }

    companion object {
        private val POPULAR_ACTOR_COMPARATOR = object : DiffUtil.ItemCallback<HomeActorNetwork>() {
            override fun areItemsTheSame(oldItem: HomeActorNetwork, newItem: HomeActorNetwork): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomeActorNetwork, newItem: HomeActorNetwork): Boolean =
                oldItem == newItem
        }
    }

    class PopularActorsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: HomeActorNetwork, cardInfo: Int, cardNavigation: CardDetailNavigation, miniCards: Boolean) {
            val imageView = if (!miniCards) view.iv_cardImage else view.iv_known_for

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.setColorSchemeColors(
                view.resources.getColor(R.color.color_main)
            )
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(view).asBitmap()
                .load("https://image.tmdb.org/t/p/w500${item.profile_path}")
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .into(imageView)

            val cardDetail = CardDetail(cardInfo, item.convertToActor())
            imageView.setOnClickListener {
                cardNavigation.onClick(cardDetail)
            }
        }

        companion object {
            fun from(parent: ViewGroup, miniCards: Boolean = false): PopularActorsViewHolder {
                val layout = if (!miniCards) R.layout.cards_list_item else R.layout.item_mini_known_for_cards

                val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
                return PopularActorsViewHolder(view)
            }
        }
    }
}

class TrendingAdapter(
    val cardInfo: Int,
    val cardNavigation: CardDetailNavigation
) : ListAdapter<Card, TrendingAdapter.TrendingViewHolder>(CardDiffCallback()) {

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, cardInfo, cardNavigation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder =
        TrendingViewHolder.from(parent)

    class TrendingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun from(parent: ViewGroup): TrendingViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cards_list_item, parent, false)
                return TrendingViewHolder(view)
            }
        }

        fun bind(item: Card, cardInfo: Int, navigation: CardDetailNavigation) {

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.setColorSchemeColors(
                view.resources.getColor(R.color.color_main)
            )
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(view).asBitmap()
                .load("https://image.tmdb.org/t/p/w500${item.image}")
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .into(view.iv_cardImage)

            val cardDetail = CardDetail(cardInfo, item)
            view.iv_cardImage.setOnClickListener {
                navigation.onClick(cardDetail)
            }
        }
    }
}