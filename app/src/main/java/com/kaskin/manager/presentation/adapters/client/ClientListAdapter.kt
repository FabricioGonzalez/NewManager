package com.kaskin.manager.presentation.adapters.client

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.kaskin.manager.R
import com.kaskin.manager.domain.clients.entities.Client

class ClientListAdapter : Adapter<ClientListAdapter.ClientListViewHolder>() {
    class ClientListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val codeClient: TextView
        val clientName: TextView
        /*val textCreationDateView: TextView*/

        init {
            codeClient = view.findViewById(R.id.client_code)
            clientName = view.findViewById(R.id.client_name)
            /*textCreationDateView = view.findViewById(R.id.header_view_data_creation)*/
        }
    }

    private var dataSet: List<Client> = emptyList()

    fun SetDataSet(list: List<Client>) {
        dataSet = list

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientListViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.client_list_adapter, parent, false)

        return ClientListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientListViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        holder.codeClient.text = dataSet[position].codigoCliente.toString()
        holder.clientName.text = dataSet[position].razaoSocial
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
