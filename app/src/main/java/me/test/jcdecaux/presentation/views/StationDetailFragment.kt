package me.test.jcdecaux.presentation.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_station_detail.*
import me.test.jcdecaux.R

/**
 * A simple [Fragment] subclass.
 */
class StationDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_station_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null) {
            var arg = StationDetailFragmentArgs.fromBundle(arguments!!)
            stationName.text = arg.station.name
        }

    }


}
