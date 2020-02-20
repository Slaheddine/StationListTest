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
import me.test.jcdecaux.presentation.adapter.StationsRecyclerAdapter
import me.test.jcdecaux.presentation.model.StationItem
import me.test.jcdecaux.presentation.viewmodels.StationViewModel
import org.koin.android.ext.android.inject


class StationListFragment : Fragment() {

    val stationsListViewModel by inject<StationViewModel>()

    lateinit var stationListAdapter: StationsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_station_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView();
        stationsListViewModel.loadStation()
    }

    fun initRecycleView() {
        stationListView.layoutManager = LinearLayoutManager(activity)

        stationListAdapter = StationsRecyclerAdapter(object : OnItemClickListener {
            override fun onItemClick(station: StationItem) {
                openDetailStation(station)
            }
        })

        stationListView.adapter = stationListAdapter

        val dividerItemDecoration = DividerItemDecoration(
            activity,
            LinearLayoutManager.VERTICAL
        )
        stationListView.addItemDecoration(dividerItemDecoration)
    }

    fun initView() {

        initRecycleView()

        stationsListViewModel.stationListLiveData.observe(viewLifecycleOwner, Observer { result : List<StationItem> ->
            manageDataResponse(result)
        })

        stationsListViewModel.failure.observe(viewLifecycleOwner, Observer { result : Unit ->
            manageFailureResponse()
        })

        progressBar.visibility = View.VISIBLE
    }

    fun manageDataResponse(stations  : List<StationItem>) {
        progressBar.visibility = View.GONE
        stationListAdapter.addStationsList(stations)
    }

    fun manageFailureResponse() {
        progressBar.visibility = View.GONE
    }

    fun openDetailStation(station : StationItem) {
        var action = StationListFragmentDirections.actionStationListActivityFragmentToStationDetailFragment2(station);
        findNavController().navigate(action)
    }
}
