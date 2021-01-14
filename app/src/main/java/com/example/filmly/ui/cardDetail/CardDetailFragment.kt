package com.example.filmly.ui.cardDetail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.filmly.R
import com.example.filmly.data.model.CardDetail
import com.example.filmly.data.model.FastBlur
import com.example.filmly.repository.ServicesRepository
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_card_detail.view.*


class CardDetailFragment : Fragment() {
    private lateinit var repository: ServicesRepository
    private val viewModel: CardDetailViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CardDetailViewModel(repository) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card_detail, container, false)

        repository = ServicesRepository.getInstance(requireContext())

        view.app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val offsetAlpha: Float = appBarLayout.y / view.app_bar.getTotalScrollRange()
            view.iv_cardDetailImageBlur.setAlpha(offsetAlpha * -1)
        })

        (view.toolbar.navigationIcon as LayerDrawable).findDrawableByLayerId(R.id.seta_cardDetail).setTint(view.resources.getColor(R.color.white))
        view.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        val circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val detail = arguments?.getSerializable("detail") as CardDetail

        view.tv_titleDetail.text = detail.card.name


        Glide.with(view).asBitmap()
            .load("https://image.tmdb.org/t/p/w500${detail.card.image}")
            .placeholder(circularProgressDrawable)
            .error(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    view.iv_cardDetailImage.setImageBitmap(resource)
                    view.iv_cardDetailImageBlur.setImageBitmap(FastBlur.doBlur(resource, 10, false))
                }
                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })


        view.tv_sinopseCardDetail.text = detail.card.descricao

        view.btn_addToLists.setOnClickListener {
            Log.i("Button", "antes: ${it.isSelected}")
            it.isSelected = !it.btn_addToLists.isSelected
            Log.i("Button", "depois: ${it.isSelected}")

            when (detail.cardInfo) {
                CardDetail.FILM -> viewModel.insertFilm(detail.card as Film)
                CardDetail.SERIE -> viewModel.insertSerie(detail.card as Serie)
                CardDetail.ACTOR -> viewModel.insertActor(detail.card as Actor)
            }
            Toast.makeText(context, "Adicionado", Toast.LENGTH_SHORT).show()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.tv_sinopseCardDetail.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        return view
    }
}