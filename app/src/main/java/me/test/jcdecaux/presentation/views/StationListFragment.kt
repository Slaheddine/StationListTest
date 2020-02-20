package me.test.jcdecaux.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_station_list.*
import me.test.jcdecaux.R
import me.test.jcdecaux.presentation.adapter.OnItemClickListener
import me.test.jcdecaux.presentation.adapter.stationsRecyclerAdapter
import me.test.jcdecaux.presentation.model.StationEntity
import me.test.jcdecaux.presentation.viewmodels.StationViewModel


class StationListFragment : Fragment() {

    val moviesListViewModel = StationViewModel()

    lateinit var movieListAdapter: stationsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_station_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView();
        moviesListViewModel.loadStation()
    }

    fun initRecycleView() {
        stationListView.layoutManager = LinearLayoutManager(activity)

        movieListAdapter = stationsRecyclerAdapter(object : OnItemClickListener {
            override fun onItemClick(station: StationEntity) {
                openDetailStation(station)
            }
        })

        stationListView.adapter = movieListAdapter

        val dividerItemDecoration = DividerItemDecoration(
            activity,
            LinearLayoutManager.VERTICAL
        )
        stationListView.addItemDecoration(dividerItemDecoration)
    }

    fun initView() {

        initRecycleView()

        moviesListViewModel.stationListLiveData.observe(viewLifecycleOwner, Observer { result : List<StationEntity> ->
            manageDataResponse(result)
        })

        moviesListViewModel.failure.observe(viewLifecycleOwner, Observer { result : Unit ->
            manageFailureResponse()
        })

        progressBar.visibility = View.VISIBLE
    }

    fun manageDataResponse(stations  : List<StationEntity>) {
        progressBar.visibility = View.GONE
        movieListAdapter.addStationsList(stations)
    }

    fun manageFailureResponse() {
        progressBar.visibility = View.GONE
    }

    fun openDetailStation(station : StationEntity) {
        var action = StationListFragmentDirections.actionStationListActivityFragmentToStationDetailFragment2(station);
        findNavController().navigate(action)
    }
}
