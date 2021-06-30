package com.example.completeapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.completeapp.R
import com.example.completeapp.adapter.AdapterData
import com.example.completeapp.data.Tesla
import com.example.completeapp.databinding.FragmentBBinding
import com.example.completeapp.databinding.ViewsLayout2Binding
import com.example.completeapp.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentB : Fragment(), AdapterData.AdapterDataInterface {
    lateinit var binding: ViewsLayout2Binding
    lateinit var bind: FragmentBBinding
    val viewModelinstance: MyViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.views_layout2, container, false)
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false)
        bind.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiDataFrag()
        val data = arguments?.getString("my text data")
        if (data != null) {
            Log.d("my text data", data)
        }
        val datatwo = arguments?.getString("my title")
        datatwo?.let { Log.d("my text data", it) }
        val datathree = arguments?.getString("my desc")
        datathree?.let { Log.d("my text data", it) }
        val datafour = arguments?.getString("my image")
        datafour?.let { Log.d("my text data", it) }
        val datafive = arguments?.getString("my content")
        datafive?.let { Log.d("my text data", it) }
        binding.text1.text = data
        binding.text2.text = datatwo
        binding.textView3.text = datathree
        Glide.with(this).load(datafour).into(binding.img)
        binding.text4.text = datafive
    }
    fun ApiDataFrag(){
        val rc = bind.rcv2
        val nameObserver = Observer { newData: Tesla? ->
            newData?.let { mydata(it, rc) }
        }
        viewModelinstance.liveData.observe(viewLifecycleOwner, nameObserver)
        //let keyword read
    }
    private fun mydata(teslaresponse: Tesla, rc: RecyclerView){
        Log.d("Success", teslaresponse.toString())
        val adapt = AdapterData(teslaresponse.articles)
        adapt.setData(this)
        rc.adapter = adapt
        rc.layoutManager = LinearLayoutManager(context)
    }

    override fun viewholdData(
        author: String,
        title: String,
        description: String,
        imageView: String,
        content: String
    ) {
        Log.d("my data", "cool cool frag")
    }
}