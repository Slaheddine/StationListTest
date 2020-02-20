package me.test.jcdecaux.presentation.views

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_station_list.*
import me.test.jcdecaux.R
import me.test.jcdecaux.presentation.model.PositionEntity
import me.test.jcdecaux.presentation.model.StationEntity
import me.test.jcdecaux.presentation.viewmodels.StationViewModel


class StationListFragment : Fragment() {

    val moviesListViewModel = StationViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_station_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var station = StationEntity(
            1212,
            "apiaz",
            "name",
            "adresse",
            PositionEntity(21.12121, 21.999293),
            false,
            false,
            12,
            12,
            1,
            "satus",
            1221.0)


        initView();
        moviesListViewModel.loadStation()

    }

    private fun initView() {

        moviesListViewModel.stationListLiveData.observe(viewLifecycleOwner, Observer { result : List<StationEntity> ->
            manageDataResponse(result)
        })

        moviesListViewModel.failure.observe(viewLifecycleOwner, Observer { result : Unit ->
            manageFailureResponse()
        })

        progressBar.visibility = View.VISIBLE
    }

    fun manageDataResponse(station  : List<StationEntity>) {
        progressBar.visibility = View.GONE
    }

    fun manageFailureResponse() {
        progressBar.visibility = View.GONE
    }
}

/*
 var action = StationListFragmentDirections.actionStationListActivityFragmentToStationDetailFragment2(station);
            Navigation.findNavController(view).navigate(action)
 */
