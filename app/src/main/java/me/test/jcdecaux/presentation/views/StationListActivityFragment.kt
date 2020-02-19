package me.test.jcdecaux.presentation.views

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_station_list.*
import me.test.jcdecaux.R

/**
 * A placeholder fragment containing a simple view.
 */
class StationListActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_station_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_stationListActivityFragment_to_stationDetailFragment2))
    }
}
