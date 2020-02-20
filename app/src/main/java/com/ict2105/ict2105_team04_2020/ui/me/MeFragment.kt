package com.ict2105.ict2105_team04_2020.ui.me

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.EditProfileActivity
import com.ict2105.ict2105_team04_2020.R
import com.ict2105.ict2105_team04_2020.adapter.OwnerItemListAdapter

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeFragment : Fragment() {

    private lateinit var recyclerViewItemList: RecyclerView
    private val itemList: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root:View = inflater.inflate(R.layout.fragment_me, container, false)
        val btnEdit: Button = root.findViewById(R.id.btnEdit)

        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")
        itemList.add("Assorted T-shirts")


        recyclerViewItemList = root.findViewById(R.id.recyclerViewOwnerItemList)
        recyclerViewItemList.isNestedScrollingEnabled = false
        recyclerViewItemList.layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
        recyclerViewItemList.adapter = OwnerItemListAdapter(itemList, root.context)

        btnEdit.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }

        return root
    }

}
