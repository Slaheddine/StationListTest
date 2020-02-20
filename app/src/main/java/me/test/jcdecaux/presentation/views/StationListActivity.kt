package me.test.jcdecaux.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.test.jcdecaux.R

import kotlinx.android.synthetic.main.activity_station_list.*


class StationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_list)
        setSupportActionBar(toolbar)
    }
}
