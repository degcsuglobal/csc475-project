package com.dangrover.danrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dangrover.danrecipe.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up tabs
        val tabHost = binding.tabhost
        tabHost.setup()

        val tab1 = tabHost.newTabSpec("Featured")
        tab1.setIndicator(getString(R.string.tab_Featured))
        tab1.setContent(R.id.featured)
        tabHost.addTab(tab1)

        val tab2 = tabHost.newTabSpec("Scheduled")
        tab2.setIndicator(getString(R.string.tab_Scheduled))
        tab2.setContent(R.id.scheduled)
        tabHost.addTab(tab2)

        val tab3 = tabHost.newTabSpec("Past")
        tab3.setIndicator(getString(R.string.tab_Past))
        tab3.setContent(R.id.past)
        tabHost.addTab(tab3)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}