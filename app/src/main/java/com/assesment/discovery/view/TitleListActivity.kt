package com.assesment.discovery.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assesment.discovery.R
import com.assesment.discovery.model.network.DataResponse
import com.assesment.discovery.viewmodel.TitleListViewModel
import kotlinx.android.synthetic.main.activity_title_list.*

/**
 * This activity displays list of titles for first user
 **/
class TitleListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_list)
        fetchUserTitles()
    }

    private fun fetchUserTitles() {
        val titleListViewModel = ViewModelProviders.of(this).get(TitleListViewModel::class.java)
        titleListViewModel.getUsers().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    DataResponse.Status.LOADING -> tv_status.text =
                        getString(R.string.loading_label)
                    DataResponse.Status.SUCCESS -> {
                        tv_status.visibility = View.GONE
                        rv_title.visibility = View.VISIBLE
                        rv_title.layoutManager = LinearLayoutManager(this)
                        rv_title.addItemDecoration(DividerItemDecorator(this))
                        val titleListAdapter = TitleListAdapter(it.data!!)
                        rv_title.adapter = titleListAdapter
                    }
                    DataResponse.Status.ERROR -> {
                        tv_status.text = getString(R.string.error_label)
                    }
                }
            }
        })
    }
}
