package com.example.challengechap4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.challengechap4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var pref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            val name = binding.regname.text.toString()
            val username = binding.regUsername.text.toString()
            val password = binding.regPassword.text.toString()

            val regist = pref.edit()
            regist.putString("nama", name)
            regist.putString("username", username)
            regist.putString("password", password)
            regist.apply()
            Toast.makeText(context,"Registrasi Anda Sukses", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)

        }


    }



}