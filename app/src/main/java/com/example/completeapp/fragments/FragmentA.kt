package com.example.completeapp.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.completeapp.R
import com.example.completeapp.adapter.AdapterData
import com.example.completeapp.data.Tesla
import com.example.completeapp.databinding.FragmentABinding
import com.example.completeapp.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer


class FragmentA() : Fragment(), AdapterData.AdapterDataInterface{
    var NavCont: NavController?=null
    lateinit var binding: FragmentABinding
    val viewModelinstance: MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiDataFrag()
        //viewModelinstance.ApiData()
        NavCont = Navigation.findNavController(view)
    }
    fun ApiDataFrag(){
        val rc = binding.rcv
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
        //adapt.setData(this)
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
        Log.d("my frag first", author)
        Log.d("my frag first", description)
        Log.d("my frag first", imageView)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("AlertDialog")
        builder.setMessage("Do you wanna jump to next frag")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
            val bundle = bundleOf(Pair("my text data",author), Pair("my title",title), Pair("my desc", description), Pair("my image", imageView), Pair("my content", content))
            NavCont?.navigate(R.id.action_fragmentA_to_fragmentB, bundle)
        })
        builder.setNegativeButton("No", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    }
