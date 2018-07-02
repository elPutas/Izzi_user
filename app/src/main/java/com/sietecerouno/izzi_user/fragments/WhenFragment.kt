package com.sietecerouno.izzi_user.fragments

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity

import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.assets.ListenerTab
import com.sietecerouno.izzi_user.modals.ChooseAddressActivity
import com.sietecerouno.izzi_user.modals.DetailReqActivity
import com.sietecerouno.izzi_user.utils.LookingForaHelpActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


open class WhenFragment : Fragment()
{
    private lateinit var listenerTab        : ListenerTab

    private var myDate                      : Date = Date()
    private var mParam1                     : String? = null
    private var mParam2                     : String? = null

    private var timeDate_arr                : ArrayList<Int>? = ArrayList()

    private lateinit var calendarView       : CalendarView
    private lateinit var calendarContainer  : LinearLayout
    private  var total                      : TextView? = null

    private lateinit var timePicker         : TimePicker
    private lateinit var timeContainer      : LinearLayout
    private lateinit var address_txt        : TextView

    private lateinit var db                 : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context?) {
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listenerTab = activity as ListenerTab
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ListenerTab")
        }

        super.onAttach(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_when, container, false)

        db = FirebaseFirestore.getInstance()


        address_txt = v.findViewById<TextView>(R.id.address_txt)

        timeDate_arr?.add(0)
        timeDate_arr?.add(0)
        timeDate_arr?.add(0)
        timeDate_arr?.add(0)
        timeDate_arr?.add(0)

        total = v.findViewById(R.id.total_value)

        calendarView = v.findViewById(R.id.calendarView)
        calendarContainer = v.findViewById<LinearLayout>(R.id.calendarContainer)

        timePicker = v.findViewById<TimePicker>(R.id.timePicker)
        timeContainer = v.findViewById<LinearLayout>(R.id.timeContainer)

        calendarContainer.visibility=View.GONE
        timeContainer.visibility=View.GONE

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->

            myDate = Date(year,  month  , dayOfMonth )
            timeDate_arr?.set(0, dayOfMonth)
            timeDate_arr?.set(1, month)
            timeDate_arr?.set(2, year)

            //val date_text = v.findViewById<TextView>(R.id.date_text) as TextView
            //date_text.text = dayOfMonth.toString() + "/" + month + "/" + year
        }

        //val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH)

        timePicker?.setOnTimeChangedListener { view, h, m ->
            timeDate_arr?.set(3, h)
            timeDate_arr?.set(4, m)

            val date_text = v.findViewById<TextView>(R.id.date_text) as TextView
            val dateToShow = Date(timeDate_arr!![0], timeDate_arr!![1], timeDate_arr!![0], timeDate_arr!![3], timeDate_arr!![4], 0)

            val date_str = "$timeDate_arr!![0] $timeDate_arr!![1] $timeDate_arr!![2]"

            //val date = LocalDate.parse(date_str, formatter)

            date_text.text = dateToShow.toString()

            BaseActivity.idReqDate_date = dateToShow
        }



        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.address_item -> gotoNew()
                R.id.date_item -> gotoDate()
                R.id.btn_okDate -> selectDate()
                R.id.btn_okTime -> selectTime()
                R.id.btn_next -> onNext()
                R.id.btn_detail -> gotoDetail()

            }
        }

        val address_btn = v.findViewById<LinearLayout>(R.id.address_item) as LinearLayout
        val date_btn = v.findViewById<LinearLayout>(R.id.date_item) as LinearLayout
        val ok_btnDate = v.findViewById<TextView>(R.id.btn_okDate) as TextView
        val ok_btnTime = v.findViewById<TextView>(R.id.btn_okTime) as TextView
        val btn_next = v.findViewById<TextView>(R.id.btn_next) as TextView
        val btn_detail = v.findViewById<TextView>(R.id.btn_detail) as TextView

        address_btn.setOnClickListener (onClickListener)
        date_btn.setOnClickListener (onClickListener)
        ok_btnDate.setOnClickListener (onClickListener)
        btn_next.setOnClickListener (onClickListener)
        btn_detail.setOnClickListener (onClickListener)


        ok_btnTime.setOnClickListener (onClickListener)

        return v
    }



    private fun onNext()
    {
        createReq()
    }

    fun gotoLooking()
    {
        listenerTab.onClickTab(2)
        val i = Intent(context, LookingForaHelpActivity::class.java)
        startActivity(i)
    }


    private fun gotoDetail()
    {
        val i = Intent(context, DetailReqActivity::class.java)
        startActivity(i)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean)
    {
        if(BaseActivity.idAddres!="")
            address_txt.text = BaseActivity.idAddres

        if(total!=null)
            total?.text = BaseActivity.idReqTotal_txt.toString()

        super.setUserVisibleHint(isVisibleToUser)
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

    fun createReq()
    {


        val refUser: DocumentReference =  db.collection("usuarios").document(BaseActivity.idUser)

        val items = HashMap<String, Any>()
        items.put("direccion", BaseActivity.idAddress_txt)
        items.put("duracionTotal", BaseActivity.idReqLapse_txt)
        items.put("empleada_seleccionada", BaseActivity.idReqHelpSelect_txt)
        items.put("creacion", Date())
        items.put("fecha", BaseActivity.idReqDate_date)
        items.put("empleadas", BaseActivity.idReqHelp_arr)
        items.put("valorTotal", BaseActivity.idReqTotal_txt)
        items.put("notificacion", false)
        items.put("servicios", BaseActivity.idReqService_arr)
        items.put("estado", 1)
        items.put("user",  refUser)


        db.collection("pedidos").add(items).addOnSuccessListener(OnSuccessListener<DocumentReference> {
            doc: DocumentReference? ->
            println("ok")
            if (doc != null) {
                BaseActivity.idReq = doc.id
            }
            gotoLooking()
        }).addOnFailureListener{
            println("error")
        }




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
