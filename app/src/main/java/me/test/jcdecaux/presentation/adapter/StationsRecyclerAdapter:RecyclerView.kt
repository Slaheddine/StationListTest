package me.test.jcdecaux.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.station_item.view.*
import me.test.jcdecaux.R
import me.test.jcdecaux.presentation.model.StationEntity

class StationsRecyclerAdapter(private val onItemClickListener: OnItemClickListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfStations = arrayListOf<StationEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StationRecycleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.station_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listOfStations.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val stationViewHolder = viewHolder as StationRecycleViewHolder
        stationViewHolder.bindView(listOfStations[position], onItemClickListener)
    }

    fun addStationsList(newList: List<StationEntity>?) {
        if ((newList!=null)&&(newList?.size > 0)) {
            var oldSize = listOfStations.size
            listOfStations.addAll(newList)
            var sizeNew = listOfStations.size
            notifyItemRangeInserted(oldSize, sizeNew)
        }
    }

    fun clear() {
        listOfStations.clear()
        notifyDataSetChanged();
    }
}

class StationRecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(stationItem: StationEntity, onItemClickListener : OnItemClickListener) {
        itemView.stationName.text = stationItem.name
        itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(stationItem)
        })
    }
}

interface OnItemClickListener {
    fun onItemClick(stationItem : StationEntity)
}