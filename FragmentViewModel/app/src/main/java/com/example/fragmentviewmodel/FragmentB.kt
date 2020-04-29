package com.example.fragmentviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_a.*

class FragmentB : Fragment() {
    private var  viewModel: SharedViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_b, container, false)
        val ok = v.findViewById<Button>(R.id.button_ok)
        ok.setOnClickListener(){ viewModel?.setText(edit_text.text) }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }
        viewModel?.getText()?.observe(viewLifecycleOwner, Observer<CharSequence?> {
                charSequence -> edit_text.setText(charSequence)
        })
    }


}
