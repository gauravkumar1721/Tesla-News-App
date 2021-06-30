package com.example.completeapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.completeapp.data.TeslaData
import com.example.completeapp.databinding.FragmentBBinding
import com.example.completeapp.databinding.ViewsLayout2Binding
import com.example.completeapp.databinding.ViewsLayoutBinding
import com.example.completeapp.fragments.FragmentB
import java.util.*

class AdapterData(val teslaData: List<TeslaData>): RecyclerView.Adapter<HolderForViews>(), HolderForViews.viewholdercommunicator  {

    interface AdapterDataInterface{
        fun viewholdData(author: String, title: String, description: String, imageView: String, content: String)
    }
    lateinit var obj: AdapterDataInterface

    fun setData(data: AdapterDataInterface){
        obj = data
    }
    override fun viewholdData(
        author: String,
        title: String,
        description: String,
        imageView: String,
        content: String
    ) {
        Log.d("adapter", author)
        Log.d("adapter", description)
        Log.d("adapter", imageView)
        obj.viewholdData(author, title, description, imageView, content)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderForViews {
     val binding =  ViewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val bind = ViewsLayout2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderForViews(binding, bind)
    }

    override fun onBindViewHolder(holder: HolderForViews, position: Int) {
        val tesdata = teslaData[position]
        holder.values(this)
        holder.bind(tesdata)
    }

    override fun getItemCount(): Int {
        return teslaData.size
    }
}