package com.indaco.daggertestapp.ui.screens.main.bart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.espresso.TestCountingIdlingResource
import com.indaco.daggertestapp.data.model.bart.BartStation
import com.indaco.daggertestapp.databinding.ActivityGitHubBinding
import com.indaco.daggertestapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BartActivity : BaseActivity<ActivityGitHubBinding>() {

    private val viewModel: BartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingContentView(ActivityGitHubBinding.inflate(layoutInflater))

        init()
    }

    private fun init() {
        observeData()

        binding.fetch.setOnClickListener { fetchData() }
    }

    private fun observeData() {
        viewModel.allStations.observe(this, ::setStations)

        viewModel.errorLiveData.observe(this, ::showError)
    }

    private fun setStations(stations: List<BartStation>?) {
        hideProgress()
        if (stations == null)
            return

        binding.recyclerView.adapter = BartStationAdapter(stations)
    }

    private fun showError(error: String) {
        hideProgress()
        AlertDialog.Builder(this)
            .setTitle(R.string.error_title)
            .setMessage(error)
            .show()
    }

    private fun fetchData() {
        showProgress()
        viewModel.getStations()
    }

    private fun showProgress() {
        // show progress layout
        TestCountingIdlingResource.increment()
    }

    private fun hideProgress() {
        // hide progress layout
        TestCountingIdlingResource.decrement()
    }

}