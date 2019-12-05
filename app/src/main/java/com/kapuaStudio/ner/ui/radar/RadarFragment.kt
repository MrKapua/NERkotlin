package com.kapuaStudio.ner.ui.radar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kapuaStudio.ner.R
import com.kapuaStudio.ner.ui.buscar.RadarViewModel

class RadarFragment : Fragment ()
{
    private lateinit var radarViewModel: RadarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        radarViewModel = ViewModelProviders.of(this).get(RadarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_radar, container, false)
        val textView : TextView = root.findViewById(R.id.text_radar)
        radarViewModel.text.observe(this, Observer { textView.text = it
        })
        return root
    }
}
