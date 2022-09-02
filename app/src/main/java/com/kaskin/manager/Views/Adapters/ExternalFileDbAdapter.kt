package com.kaskin.manager.Views.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kaskin.manager.R

class ExternalFileDbAdapter() :
    RecyclerView.Adapter<ExternalFileDbAdapter.ExternalFileDbViewHolder>() {
    class ExternalFileDbViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.header_view_code)
        }
    }

    private var dataSet: Array<String> = emptyArray()

    fun SetDataSet(list: Array<String>) {
        dataSet = list

        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ExternalFileDbViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.external_file_db_layout, viewGroup, false)

        return ExternalFileDbViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ExternalFileDbViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
