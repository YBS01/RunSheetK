package com.example.runsheetk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.runsheetk.SheetFragmentDirections
import com.example.runsheetk.databinding.CueItemBinding
import com.example.runsheetk.models.Cues
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase

class RvCuesAdapter(private val cueList: java.util.ArrayList<Cues>): RecyclerView.Adapter<RvCuesAdapter.ViewHolder>() {

    class ViewHolder(val binding: CueItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        return ViewHolder(CueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")

        return cueList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")

        val currentCue = cueList[position]

        holder.apply{



            binding.apply {
                textViewCueName.text = currentCue.name
                textViewEstTime.text = currentCue.time
                textViewCategory.text = currentCue.category
                textViewNotes.text = currentCue.notes

                when (currentCue.status) {
                    "Standby" -> {
                        rvCueItem.setBackgroundColor(0xFFED930D.toInt()) // Orange color
                    }
                    "Live" -> {
                        rvCueItem.setBackgroundColor(0xFFCC0000.toInt()) // Red color
                    }
                    "Complete" -> {
                        rvCueItem.setBackgroundColor(0xFF00B000.toInt()) // Green color
                    }
                }


                rvCueItem.setOnClickListener {
                    val action = SheetFragmentDirections.actionSheetFragmentToUpdateFragment(
                    currentCue.cueId.toString(),
                    currentCue.time.toString(),
                    currentCue.category.toString(),
                    currentCue.notes.toString(),
                    currentCue.name.toString(),
                    currentCue.status.toString()
                    )

                    findNavController(holder.itemView).navigate(action)
                }

                rvCueItem.setOnLongClickListener {
                    MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Delete item ${currentCue.name}")
                    .setMessage("Are you sure you want to delete ${currentCue.name}?")

                    .setPositiveButton("Yes") { _, _ ->

                        val firebaseRef = FirebaseDatabase.getInstance().getReference("cues")
                        firebaseRef.child(currentCue.cueId.toString()).removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(holder.itemView.context, "Item successfully deleted", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {error ->
                                Toast.makeText(holder.itemView.context, "Failed to delete item. Error message: ${error.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .setNegativeButton("No") { _, _ ->
                        Toast.makeText(holder.itemView.context, "Canceled", Toast.LENGTH_SHORT).show()
                    }
                    .show()



                    return@setOnLongClickListener true
                }


            }
        }
    }


}