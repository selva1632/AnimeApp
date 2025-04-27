package com.selva.anime.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selva.anime.databinding.ActivityHomeBinding
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.ui.home.adapter.VerticalAdapter
import com.selva.anime.ui.home.eventlistener.AnimeClickListener
import com.selva.anime.presentation.viewmodel.AnimeViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewmodel: AnimeViewmodel by viewModels()

    // TODO click listener to launch detail activity
    private val listener by lazy {
        object : AnimeClickListener {
            override fun onClick(item: Any) {
                when (item) {
                    is AnimeItem -> {

                    }

                    else -> {}
                }
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