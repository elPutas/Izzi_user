package com.sietecerouno.izzi_user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.sections.HomeActivity
import com.sietecerouno.izzi_user.sections.LoginActivity

class MainActivity : BaseActivity()
{



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val user:FirebaseUser? = FirebaseAuth.getInstance().currentUser


        if (user != null) {
            println("user not null")
            //goto home
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }else{
            println("user null")
            //goto log
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

    }
}
