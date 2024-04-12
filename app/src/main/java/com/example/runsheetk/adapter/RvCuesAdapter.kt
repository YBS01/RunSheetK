package com.example.runsheetk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.runsheetk.databinding.CueItemBinding
import com.example.runsheetk.models.Cues

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
            }
        }
    }


}