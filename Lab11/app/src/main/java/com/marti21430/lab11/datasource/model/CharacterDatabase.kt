package com.marti21430.lab11.datasource.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.marti21430.lab11.R
import com.marti21430.lab11.datasource.local_source.User

class CharacterDatabase (
    private val dataSet: MutableList<User>,
    private val listeners: UserClickListeners
) : RecyclerView.Adapter<CharacterDatabase.ViewHolder>() {

    class ViewHolder(view: View, val listeners: UserClickListeners): RecyclerView.ViewHolder(view) {
        private var txtname: TextView = view.findViewById(R.id.text_characterDetails_name)
        private val txtspecies: TextView = view.findViewById(R.id.text_characterDetails_species)
        private val txtstatus: TextView = view.findViewById(R.id.text_characterDetails_status)
        private val txtgender: TextView = view.findViewById(R.id.text_characterDetails_gender)
        private val txtorigin: TextView = view.findViewById(R.id.text_characterDetails_origin)
        private val txtepisodes: TextView = view.findViewById(R.id.text_characterDetails_episodes)

        fun setData(user: User) {
            txtname.text = user.name
            txtspecies.text = user.species
            txtstatus.text = user.status
            txtgender.text = user.gender
            txtorigin.text = user.origin
            txtepisodes.text = user.episodes.toString()
        }
    }
    interface UserClickListeners {
        fun onEditClicked(user: User)
        fun onDeleteClicked(user: User)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_details, parent, false)
        return ViewHolder(view, listeners)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}