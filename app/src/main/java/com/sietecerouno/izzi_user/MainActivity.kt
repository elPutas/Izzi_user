package com.sietecerouno.izzi_user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.sections.HomeActivity
import com.sietecerouno.izzi_user.sections.LoginActivity
import com.sietecerouno.izzi_user.sections.PreHomeActivity

class MainActivity : BaseActivity()
{



    override fun onCreate(savedInstanceState: Bundle?)
    {
        FacebookSdk.sdkInitialize(applicationContext)
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val user:FirebaseUser? = FirebaseAuth.getInstance().currentUser
        FirebaseAuth.getInstance().signOut()

        if (user != null) {
            idUser = user.uid

            //goto home
            val i = Intent(this, PreHomeActivity::class.java)
            startActivity(i)
            finish()
        }else{

            //goto log
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

    }
}
