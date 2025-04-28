package com.selva.anime.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.selva.anime.databinding.ActivityAnimeDetailBinding
import com.selva.anime.presentation.constants.AnimeConstant
import com.selva.anime.presentation.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeDetailBinding

    private val viewmodel by viewModels<DetailViewModel>()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        with(binding.webviewAnime) {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
        }

        val animeId = intent.getIntExtra(AnimeConstant.ANIME_ID, 0)
        observe()
        viewmodel.fetchAnimeById(animeId)
    }

    private fun observe() {
        viewmodel.detailInfo.observe(this) {
            it.url?.let { binding.webviewAnime.loadUrl(it) }
        }
    }
}