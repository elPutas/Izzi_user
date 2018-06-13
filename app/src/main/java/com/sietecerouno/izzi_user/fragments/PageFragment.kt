package com.sietecerouno.izzi_user.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sietecerouno.izzi_user.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PageFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        val page = arguments!!.getInt(NUM_PAGE)

        return view
    }


    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val NUM_PAGE = "numPage"
        fun newInstance(page: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt(NUM_PAGE, page)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
