package com.marti21430.lab11.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marti21430.lab11.R
import com.marti21430.lab11.datasource.api.RetrofitInstance
import com.marti21430.lab11.datasource.model.Character
import com.marti21430.lab11.datasource.model.CharactersResponse
import com.marti21430.lab11.ui.KEY_EMAIL
import com.marti21430.lab11.ui.adapters.CharacterAdapter
import com.marti21430.lab11.ui.dataStore
import com.marti21430.lab11.ui.removePreferencesValue
import com.google.android.material.appbar.MaterialToolbar
import com.marti21430.lab11.datasource.local_source.Database
import com.marti21430.lab11.datasource.local_source.User
import com.marti21430.lab11.datasource.model.CharacterDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment(R.layout.fragment_character_list), CharacterAdapter.RecyclerViewCharactersEvents {

    private lateinit var characters: MutableList<Character>
    private lateinit var adapter: CharacterAdapter
    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerCharacters: RecyclerView

    private lateinit var database: Database
    private lateinit var userAdapter: CharacterDatabase
    private val userList: MutableList<User> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCharacters = view.findViewById(R.id.recycler_characters)
        toolbar = view.findViewById(R.id.toolbar_characterList)

        setToolbar()
        setListeners()
        getCharacters()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(setOf(R.id.characterListFragment))

        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun setListeners() {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_item_asc -> {
                    characters.sortBy { character -> character.name }
                    adapter.notifyDataSetChanged()
                    true
                }

                R.id.menu_item_des -> {
                    characters.sortByDescending { character -> character.name }
                    adapter.notifyDataSetChanged()
                    true
                }

                R.id.menu_item_logout -> {
                    logout()
                    true
                }
                else -> true
            }
        }
    }

    private fun getCharacters() {
        RetrofitInstance.api.getCharacters().enqueue(object: Callback<CharactersResponse> {
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful) {
                    val res: MutableList<Character>? = response.body()?.results
                    setupRecycler(res ?: mutableListOf())
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.error_fetching), Toast.LENGTH_LONG).show()
            }

        })
    }


    private fun setupRecycler(characters: MutableList<Character>) {

        this.characters = characters

        adapter = CharacterAdapter(this.characters, this)
        recyclerCharacters.layoutManager = LinearLayoutManager(requireContext())
        recyclerCharacters.setHasFixedSize(true)
        recyclerCharacters.adapter = adapter
    }

    private fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            requireContext().dataStore.removePreferencesValue(KEY_EMAIL)
            CoroutineScope(Dispatchers.Main).launch {
                requireView().findNavController().navigate(
                    CharacterListFragmentDirections.actionCharacterListFragmentToLoginFragment()
                )
            }
        }
    }

    override fun onItemClicked(character: Character) {
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(
            character.id.toInt()
        )

        requireView().findNavController().navigate(action)
    }

}