package com.example.completeapp.adapter

import android.app.AlertDialog
import android.content.ClipDescription
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.completeapp.data.TeslaData
import com.example.completeapp.databinding.FragmentBBinding
import com.example.completeapp.databinding.ViewsLayout2Binding
import com.example.completeapp.databinding.ViewsLayoutBinding

class HolderForViews(val binding: ViewsLayoutBinding, val bind: ViewsLayout2Binding): RecyclerView.ViewHolder(binding.root) {
    interface viewholdercommunicator {
        fun viewholdData(
            author: String,
            title: String,
            description: String,
            imageView: String,
            content: String
        )
    }

    lateinit var obj: viewholdercommunicator

    fun values(data: viewholdercommunicator) {
        obj = data
    }

    fun bind(teslaData: TeslaData) {

        binding.text1.text = teslaData.author
        binding.text2.text = teslaData.title
        bind.text1.text = teslaData.author
        bind.text2.text = teslaData.title
        bind.textView3.text = teslaData.description
        bind.text4.text = teslaData.content
        Glide.with(itemView.context).load(teslaData.urlToImage).into(bind.img)
        binding.text1.setOnClickListener {
            //dialog
            obj.viewholdData(
                teslaData.author,
                teslaData.title,
                teslaData.description,
                teslaData.urlToImage,
                teslaData.content
            )

        }
    }
}