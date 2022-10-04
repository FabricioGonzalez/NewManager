package com.kaskin.manager.Views.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kaskin.manager.Enums.DbState
import com.kaskin.manager.Models.DbModel
import com.kaskin.manager.R

class ExternalFileDbAdapter() :
    RecyclerView.Adapter<ExternalFileDbAdapter.ExternalFileDbViewHolder>() {
    class ExternalFileDbViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dbImage: ImageView
        val textNameView: TextView
        val textSizeView: TextView
        val textCreationDateView: TextView

        init {
            dbImage = view.findViewById(R.id.db_image)
            textNameView = view.findViewById(R.id.header_view_code)
            textSizeView = view.findViewById(R.id.header_view_size)
            textCreationDateView = view.findViewById(R.id.header_view_data_creation)
        }
    }

    private var dataSet: Array<DbModel> = emptyArray()

    fun SetDataSet(list: Array<DbModel>) {
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
        val imageResource = when (dataSet[position].State) {
            DbState.Activated -> R.drawable.ic_database_view
            DbState.Desactivated -> R.drawable.ic_delete_database
            DbState.Unbound -> R.drawable.ic_database
        }
        viewHolder.dbImage.setImageResource(imageResource)
        viewHolder.textNameView.text = dataSet[position].dbname
        viewHolder.textSizeView.text = dataSet[position].size.toString()
        viewHolder.textCreationDateView.text = dataSet[position].CreationData.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
