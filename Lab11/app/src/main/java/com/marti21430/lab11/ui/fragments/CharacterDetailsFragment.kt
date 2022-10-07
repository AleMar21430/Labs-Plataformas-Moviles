package com.marti21430.lab11.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.marti21430.lab11.R
import com.marti21430.lab11.datasource.api.RetrofitInstance
import com.marti21430.lab11.datasource.model.Character
import com.google.android.material.appbar.MaterialToolbar
import com.marti21430.lab11.datasource.local_source.Database
import com.marti21430.lab11.datasource.local_source.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var txtName: TextView
    private lateinit var txtSpecies: TextView
    private lateinit var txtGender: TextView
    private lateinit var txtStatus: TextView
    private lateinit var txtOrigin: TextView
    private lateinit var txtEpisodes: TextView
    private lateinit var imageCharacter: ImageView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var  ButtonSave: Button
    private lateinit var  ButtonRefresh: Button
    private lateinit var  ButtonDelete: Button

    private lateinit var database: Database
    private lateinit var currentUser: User
    private var createUser: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            txtName = findViewById(R.id.text_characterDetails_name)
            txtSpecies = findViewById(R.id.text_characterDetails_species)
            txtGender = findViewById(R.id.text_characterDetails_gender)
            txtStatus = findViewById(R.id.text_characterDetails_status)
            txtOrigin = findViewById(R.id.text_characterDetails_origin)
            txtEpisodes = findViewById(R.id.text_characterDetails_episodes)
            imageCharacter = findViewById(R.id.image_characterDetails)
            toolbar = findViewById(R.id.toolbar_characterDetails)
            ButtonSave = findViewById(R.id.buttonSave)
            ButtonRefresh = findViewById(R.id.buttonRefresh)
            ButtonDelete = findViewById(R.id.buttonDelete)
        }

        database = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "dbname"
        ).build()

        if (args.id == 0) {
            getAPICharacter()
        } else {
            fetchUser()
        }
        setToolbar()
        setListeners()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun getAPICharacter() {
        RetrofitInstance.api.getCharacter(args.id).enqueue(object: Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful && response.body() != null) {
                    setData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.error_fetching), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setData(character: Character) {
        character.apply {
            txtName.text = name
            txtSpecies.text = species
            txtStatus.text = status
            txtGender.text = gender
            txtOrigin.text = origin.name
            txtEpisodes.text = episode.size.toString()
            imageCharacter.load(image) {
                placeholder(R.drawable.ic_downloading)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_error)
                memoryCachePolicy(CachePolicy.ENABLED)
            }
        }
    }

    private fun setListeners() {
        ButtonSave.setOnClickListener {
            createUser()
        }
        ButtonRefresh.setOnClickListener {
            getAPICharacter()
        }
        ButtonDelete.setOnClickListener {
            resetUser()
        }
    }

    private fun fetchUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = database.userDao().getUserById(args.id)
            CoroutineScope(Dispatchers.Main).launch {
                if (user != null) {
                    txtName.text = (user.name)
                    txtSpecies.text = (user.species)
                    txtStatus.text = (user.status)
                    txtGender.text = (user.gender)
                    txtOrigin.text = (user.origin)
                    txtEpisodes.text = (user.episodes.toString())
                    currentUser = user
                } else {
                    createUser = true
                    getAPICharacter()
                }
            }
        }
    }

    private fun createUser() {
        val user = User(
            name = txtName.text.toString(),
            species = txtSpecies.text.toString(),
            status = txtStatus.text.toString(),
            gender = txtGender.text.toString(),
            origin = txtOrigin.text.toString(),
            episodes = txtEpisodes.text.toString().toInt()
        )
        CoroutineScope(Dispatchers.IO).launch {
            database.userDao().insert(user)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    requireContext(),
                    "Data guardada exitosamente",
                    Toast.LENGTH_LONG
                ).show()
                requireActivity().onBackPressed()
            }
        }
    }

    private fun resetUser() {
        getAPICharacter()
        val updatedUser = currentUser.copy(
            name = txtName.text.toString(),
            species = txtSpecies.text.toString(),
            status = txtStatus.text.toString(),
            gender = txtGender.text.toString(),
            origin = txtOrigin.text.toString(),
            episodes = txtEpisodes.text.toString().toInt()
        )

        CoroutineScope(Dispatchers.IO).launch {
            database.userDao().update(updatedUser)
        }
    }

}