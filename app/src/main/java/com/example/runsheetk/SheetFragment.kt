package com.example.runsheetk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runsheetk.adapter.RvCuesAdapter
import com.example.runsheetk.databinding.FragmentSheetBinding
import com.example.runsheetk.models.Cues
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [SheetFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class SheetFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private var _binding: FragmentSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var cueList: ArrayList<Cues>
    private lateinit var firebaseRef : DatabaseReference

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

        _binding = FragmentSheetBinding.inflate(inflater, container, false)

//        return inflater.inflate(R.layout.fragment_sheet, container, false)

        binding.createFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_sheetFragment_to_createFragment)
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }

        firebaseRef = FirebaseDatabase.getInstance().getReference("cues")
        cueList = arrayListOf()

        fetchData()

        binding.cueRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }

        return binding.root
    }

    private fun fetchData() {
//        TODO("Not yet implemented")

        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                TODO("Not yet implemented")

                cueList.clear()
                if (snapshot.exists()) {
                    for (cuesSnapshot in snapshot.children) {
                        val cue = cuesSnapshot.getValue(Cues::class.java)
                        cueList.add(cue!!)
                    }
//                    binding.cueRv.adapter = RvCuesAdapter(cueList)
                }
                val cueAdapter = RvCuesAdapter(cueList)

                binding.cueRv.adapter = cueAdapter

            }

            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")


                Toast.makeText(context, "error: ${error.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SheetFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SheetFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}