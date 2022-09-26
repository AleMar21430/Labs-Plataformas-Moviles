package com.marti21430.lab10.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.marti21430.lab10.R
import com.marti21430.lab10.databinding.FragmentLoginBinding
import com.marti21430.lab10.ui.activities.MyApplication.Companion.username
import com.marti21430.lab10.ui.adapters.CharacterAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Login : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var Splash_Image: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = this.requireActivity().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
        view.apply {
            Splash_Image = findViewById(R.id.Splash_Image)
        }
        if (prefs.getBoolean("Logged", true)){
            findNavController().navigate(R.id.action_login_to_characterListFragment)
        }

        Splash_Image.load("https://occ.a.nflxso.net/dnm/api/v6/LmEnxtiAuzezXBjYXPuDgfZ4zZQ/AAAABekGfcjcxKQH35sJxlr2te1JNYCwXqpovwMvTf4gzZ1ihzzUY0M8YcEijgvL_ClD5aIMWEY-hKIZVmgthEVssV7SthGRm2xVXkuwzpphv6u0.png?r=a66") {
            placeholder(R.drawable.ic_downloading)
            error(R.drawable.ic_error)
            memoryCachePolicy(CachePolicy.ENABLED)
        }

        binding.ButtonLoginToWelcome.setOnClickListener {

            val inputusername = getView()?.findViewById(R.id.Text_Input_Mail) as EditText
            val inputpassword = getView()?.findViewById(R.id.Text_Input_Password) as EditText

            if (inputusername.text.toString() != username) {

                if (inputpassword.text.toString() != username) {

                    prefs.edit().putBoolean("Logged", true).apply()
                    Toast.makeText(activity, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                }
            }else{
                findNavController().navigate(R.id.action_login_to_characterListFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}