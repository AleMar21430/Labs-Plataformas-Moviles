package com.marti21430.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marti21430.lab7.databinding.FragmentRegisterBinding
import com.marti21430.lab7.MyApplication.Companion.username


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Register : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ButtonRegisterToWelcome.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_welcome)
            var newusername = getView()?.findViewById(R.id.Text_Input_Register_Mail) as EditText
            username = newusername.text.toString()
            Toast.makeText(activity, username, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}