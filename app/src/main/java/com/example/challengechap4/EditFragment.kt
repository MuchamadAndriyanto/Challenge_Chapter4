package com.example.challengechap4

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challengechap4.databinding.FragmentEditBinding
import com.example.challengechap4.dbroom.NoteData
import com.example.challengechap4.dbroom.NoteDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    var dbNote: NoteDataBase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote = NoteDataBase.getInstance(requireContext())

        // var getDataNote = activity?.intent?.getSerializableExtra("editData") as DataNote
        var getNoteData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("editData", NoteData::class.java)
        } else {
            arguments?.getParcelable("editData")
        }

        if (getNoteData != null) {
            binding.editTittle.setText(getNoteData.title)
        }
        if (getNoteData != null) {
            binding.editNotee.setText(getNoteData.content)
        }
        if (getNoteData != null) {
            binding.idNote.setText(getNoteData.id.toString())
        }

        binding.btnEditNote.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.editTittle.text.toString()
        var note = binding.editNotee.text.toString()


        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(NoteData(idNote, title, note))

        }
        Toast.makeText(requireContext(), "Data Berhasil di Edit", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_editFragment_to_homeFragment)
    }

}