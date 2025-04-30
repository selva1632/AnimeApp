package com.selva.anime.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selva.anime.databinding.ActivityHomeBinding
import com.selva.anime.presentation.HomeViewmodel
import com.selva.anime.presentation.constants.AnimeConstant
import com.selva.anime.ui.detail.AnimeDetailActivity
import com.selva.anime.ui.home.adapter.VerticalAdapter
import com.selva.anime.ui.home.contract.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewmodel: HomeViewmodel by viewModels()

    private val adapter by lazy {
        VerticalAdapter(viewmodel::handleEvent)
    }

    private fun observerEvent() {
        viewmodel.eventLiveData.observe(this) { event ->
            when (event) {
                is UiEvent.SelectAnime -> {
                    Intent(this, AnimeDetailActivity::class.java).apply {
                        putExtra(AnimeConstant.ANIME_ID, event.id)
                    }.also {
                        this.startActivity(it)
                    }
                }
            }
        }
    }

    private fun observeError() {
        viewmodel.errorLiveData.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewmodel.fetchAnime()
        observerEvent()
        observeError()

        with(binding) {
            vm = viewmodel
            lifecycleOwner = this@HomeActivity

            topAnimeRecyclerView.adapter = adapter
            topAnimeRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }


}