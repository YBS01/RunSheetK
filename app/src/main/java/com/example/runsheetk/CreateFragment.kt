package com.example.runsheetk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.runsheetk.databinding.FragmentCreateBinding
import com.example.runsheetk.databinding.FragmentSheetBinding
import com.example.runsheetk.models.Cues
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [CreateFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class CreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseRef: DatabaseReference


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create, container, false)


        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        firebaseRef = FirebaseDatabase.getInstance().getReference("cues")

        binding.saveButton.setOnClickListener {
            saveData()
        }

//        return inflater.inflate(R.layout.fragment_sheet, container, false)

//        binding.saveButton.setOnClickListener {
////            findNavController().navigate(R.id.action_createFragment_to_sheetFragment)
////            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
//        }
        return binding.root
    }

    private fun saveData() {
        val cueName = binding.editTextCueName.text.toString()
        val estTime = binding.editTextEstTime.text.toString()
        val category = binding.editTextCategory.text.toString()
        val notes = binding.editTextNotes.text.toString()

        if (cueName.isEmpty()) {
            binding.editTextCueName.error = "Please enter cue name"
            return
        }

        if (estTime.isEmpty()) {
            binding.editTextEstTime.error = "Please enter estimated time"
            return
        }

        if (category.isEmpty()) {
            Toast.makeText(context, "Please select a category", Toast.LENGTH_SHORT).show()
            return
        }

        val cueId = firebaseRef.push().key!!
        val cues = Cues(cueId, cueName, estTime, category, notes)

        firebaseRef.child(cueId).setValue(cues)
            .addOnCompleteListener {
                Toast.makeText(context, "Cue saved", Toast.LENGTH_SHORT).show()
                // Only navigate if all fields are filled
                findNavController().navigate(R.id.action_createFragment_to_sheetFragment)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error! Cue not saved: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment CreateFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            CreateFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}