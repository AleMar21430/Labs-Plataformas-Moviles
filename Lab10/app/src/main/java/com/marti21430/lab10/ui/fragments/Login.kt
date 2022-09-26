package com.marti21430.lab10.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import coil.request.CachePolicy
import com.marti21430.lab10.R
import com.marti21430.lab10.databinding.FragmentLoginBinding
import com.marti21430.lab10.ui.activities.MainActivity
import com.marti21430.lab10.ui.activities.MyApplication.Companion.username

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Login : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
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

        view.apply {
            Splash_Image = findViewById(R.id.Splash_Image)
        }

        val prefs = (activity as MainActivity).getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)

        if (prefs.getBoolean("Log", false)){
            findNavController().navigate(R.id.action_login_to_characterListFragment)
        }else {
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
                        Toast.makeText(activity, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    prefs.edit().putBoolean("Log", true)
                    prefs.edit().apply()
                    prefs.edit().commit()
                    findNavController().navigate(R.id.action_login_to_characterListFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}