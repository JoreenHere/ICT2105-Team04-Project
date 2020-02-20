package com.ict2105.ict2105_team04_2020.ui.charityOrganization

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.R
import com.ict2105.ict2105_team04_2020.adapter.OrganizationListAdapter

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CharityOrganizationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CharityOrganizationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharityOrganizationFragment : Fragment() {

    private lateinit var recyclerViewItemList: RecyclerView
    private val organizationList: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_charity_organization, container, false)

        organizationList.add("The Salvation Army")
        organizationList.add("The Salvation Army")
        organizationList.add("The Salvation Army")
        organizationList.add("The Salvation Army")
        organizationList.add("The Salvation Army")

        recyclerViewItemList = root.findViewById(R.id.recyclerViewOrganizationList)
        recyclerViewItemList.layoutManager = LinearLayoutManager(activity)
        recyclerViewItemList.adapter = OrganizationListAdapter(organizationList, root.context)

        return root
    }

}
