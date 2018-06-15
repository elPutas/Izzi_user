package com.sietecerouno.izzi_user

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by Gio on 13/06/18.
 */
open class BaseActivity : AppCompatActivity()
{
    companion object {
        //user
        var idUser = ""

        //request
        var idReq = ""
        
    }

}
