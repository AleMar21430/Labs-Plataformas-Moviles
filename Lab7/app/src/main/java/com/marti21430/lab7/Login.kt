package com.marti21430.lab7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.marti21430.lab7.databinding.FragmentLoginBinding
import com.marti21430.lab7.MyApplication.Companion.username

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ButtonToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
        binding.ButtonLoginToWelcome.setOnClickListener {

            val inputusername = getView()?.findViewById(R.id.Text_Input_Register_Mail) as EditText

            if (inputusername.text.toString() != username) {
                Toast.makeText(activity, "Usuario Incorrecto", Toast.LENGTH_SHORT).show()
            }else{
                findNavController().navigate(R.id.action_login_to_welcome)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}