package com.ict2105.ict2105_team04_2020.ui.browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.R
import com.ict2105.ict2105_team04_2020.adapter.ItemListAdapter

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BrowseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BrowseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowseFragment : Fragment() {
    private lateinit var recyclerViewItemList: RecyclerView
    private val itemList: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_browse, container, false)

        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")

        recyclerViewItemList = root.findViewById(R.id.recyclerViewItemList)
        recyclerViewItemList.layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
        recyclerViewItemList.adapter = ItemListAdapter(itemList, root.context)
        return root
    }
}
