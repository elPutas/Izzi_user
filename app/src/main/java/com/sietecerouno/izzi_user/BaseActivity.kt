package com.sietecerouno.izzi_user

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Gio on 13/06/18.
 */
open class BaseActivity : AppCompatActivity()
{
    companion object {
        val TAG = "GIO"
        //user
        var idUser = ""

        //request
        var idReq = "5TMirm0GuuODL4ebqE9n"
        var idAddres = ""
        var idAddress_txt = ""
        var idReqLapse_txt = ""
        var idReqHelpSelect_txt = ""
        var idReqTotal_txt : Int = 0 
        var idReqHelp_arr :ArrayList<String> = ArrayList()
        var idReqService_arr :ArrayList<String> = ArrayList()
        lateinit var idReqDate_date :Date
    }

}
