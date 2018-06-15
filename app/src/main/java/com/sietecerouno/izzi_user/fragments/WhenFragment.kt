package com.sietecerouno.izzi_user.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.modals.ChooseAddressActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class WhenFragment : Fragment()
{


    private var myDate                      : Date = Date()
    private var mParam1                     : String? = null
    private var mParam2                     : String? = null

    private var timeDate_arr                : ArrayList<String>? = ArrayList()

    private lateinit var calendarView       : CalendarView
    private lateinit var calendarContainer  : LinearLayout

    private lateinit var timePicker         : TimePicker
    private lateinit var timeContainer      : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_when, container, false)

        timeDate_arr?.add("")
        timeDate_arr?.add("")
        calendarView = v.findViewById<CalendarView>(R.id.calendarView)
        calendarContainer = v.findViewById<LinearLayout>(R.id.calendarContainer)

        timePicker = v.findViewById<TimePicker>(R.id.timePicker)
        timeContainer = v.findViewById<LinearLayout>(R.id.timeContainer)

        calendarContainer.visibility=View.GONE
        timeContainer.visibility=View.GONE

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->

            myDate = Date(year,  month  , dayOfMonth )
            timeDate_arr?.set(0, (dayOfMonth.toString() + "/" + month + "/" + year))

            //val date_text = v.findViewById<TextView>(R.id.date_text) as TextView
            //date_text.text = dayOfMonth.toString() + "/" + month + "/" + year
        }

        timePicker?.setOnTimeChangedListener { view, h, m ->
            timeDate_arr?.set(1, (h.toString() + ":" + m))

            val date_text = v.findViewById<TextView>(R.id.date_text) as TextView
            date_text.text = timeDate_arr.toString()
        }



        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.addres_item -> gotoNew()
                R.id.date_item -> gotoDate()
                R.id.btn_okDate -> selectDate()
                R.id.btn_okTime -> selectTime()

            }
        }

        val addres_btn = v.findViewById<LinearLayout>(R.id.addres_item) as LinearLayout
        val date_btn = v.findViewById<LinearLayout>(R.id.date_item) as LinearLayout
        val ok_btnDate = v.findViewById<TextView>(R.id.btn_okDate) as TextView
        val ok_btnTime = v.findViewById<TextView>(R.id.btn_okTime) as TextView

        addres_btn.setOnClickListener (onClickListener)
        date_btn.setOnClickListener (onClickListener)
        ok_btnDate.setOnClickListener (onClickListener)
        ok_btnTime.setOnClickListener (onClickListener)

        return v
    }

    private fun selectTime()
    {
        timeContainer.visibility=View.GONE
    }


    private fun selectDate()
    {
        calendarContainer.visibility=View.GONE
        timeContainer.visibility=View.VISIBLE
    }

    private fun gotoDate()
    {
        calendarContainer.visibility=View.VISIBLE
    }

    private fun gotoNew()
    {
        val i = Intent(context, ChooseAddressActivity::class.java)
        startActivity(i)
    }

    object DateUtils {
        @JvmStatic
        fun toSimpleString(date: Date) : String {
            val format = SimpleDateFormat("dd/MM/yyy")
            return format.format(date)
        }
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): WhenFragment {
            val fragment = WhenFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor

fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("dd/MM/yyy")
    return format.format(this)
}
