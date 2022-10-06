package com.marti21430.lab11.datasource.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.marti21430.lab11.R

class CharacterDatabase {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val txtname: TextInputEditText = view.findViewById(R.id.text_characterDetails_name)
        private val txtspecies: TextInputEditText = view.findViewById(R.id.text_characterDetails_species)
        private val txtstatus: TextInputEditText = view.findViewById(R.id.text_characterDetails_status)
        private val txtgender: TextInputEditText = view.findViewById(R.id.text_characterDetails_gender)
        private val txtorigin: TextInputEditText = view.findViewById(R.id.text_characterDetails_origin)
        private val txtepisodes: TextInputEditText = view.findViewById(R.id.text_characterDetails_episodes)


    }
}