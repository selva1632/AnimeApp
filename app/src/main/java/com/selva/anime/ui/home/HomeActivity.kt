package com.selva.anime.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selva.anime.databinding.ActivityHomeBinding
import com.selva.anime.ui.home.adapter.VerticalAdapter
import com.selva.anime.ui.home.eventlistener.AnimeClickListener
import com.selva.anime.presentation.HomeViewmodel
import com.selva.anime.presentation.event.AnimeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewmodel: HomeViewmodel by viewModels()

    private val listener by lazy {
        object : AnimeClickListener {
            override fun sendEvent(event: AnimeEvent) {
                viewmodel.handleEvent(event, this@HomeActivity)
            }
        }
    }

    private val adapter by lazy {
        VerticalAdapter(listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewmodel.fetchAnime()
        initObservers()

        with(binding) {
            vm = viewmodel
            lifecycleOwner = this@HomeActivity

            topAnimeRecyclerView.adapter = adapter
            topAnimeRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }

    private fun initObservers() {
        viewmodel.errorLiveData.observe(this) { error ->
            error?.let {
                Toast.makeText(this@HomeActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}