package com.sietecerouno.izzi_user.sections

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.MainActivity
import com.sietecerouno.izzi_user.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import kotlin.collections.ArrayList

class LoginActivity : BaseActivity()
{
    var fbAuth = FirebaseAuth.getInstance()
    var callbackManager: CallbackManager? = null
    lateinit var btn_fb:TextView
    lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide header
        supportActionBar?.hide()

        db = FirebaseFirestore.getInstance()

        callbackManager = CallbackManager.Factory.create();

        val btn_register = findViewById<TextView>(R.id.btn_register)
        btn_fb = findViewById<TextView>(R.id.btn_fb)
        val btn_login = findViewById<TextView>(R.id.btn_login)

        val onClickListener :View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_register -> gotoReg()
                R.id.btn_login -> gotoLog()
                R.id.btn_fb -> gotoFB()
            }
        }

        btn_register.setOnClickListener( onClickListener )
        btn_fb.setOnClickListener( onClickListener )
        btn_login.setOnClickListener( onClickListener )


    }

    private fun gotoReg()
    {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun gotoLog()
    {
        val i = Intent(this, RealLoginActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun gotoFB()
    {
        LoginManager.getInstance().logInWithReadPermissions(this@LoginActivity, Arrays.asList("email", "public_profile"))
        handleFacebookAccess()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)

    }

    private fun handleFacebookAccess()
    {
        LoginManager.getInstance().registerCallback(callbackManager!!, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val accessToken = AccessToken.getCurrentAccessToken()

                val request = GraphRequest.newMeRequest(accessToken) { obj, response ->
                    try {
                        if (obj.has("name"))
                        {
                            if (obj.has("email"))
                            {
                                val _fullName :ArrayList<String> = obj.get("name") as ArrayList<String>



                                //createUser(_name, _lName, _mail, fbAuth.uid!!)

                                val i = Intent(this@LoginActivity, PreHomeActivity::class.java)
                                startActivity(i)
                                finish()
                            }
                        }
                    } catch (e: Exception)
                    {
                        e.printStackTrace()
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email")
                request.parameters = parameters
                request.executeAsync()

            }

            override fun onCancel()
            {
                Toast.makeText(this@LoginActivity, "Cancel", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException)
            {
                Toast.makeText(this@LoginActivity, "onError", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun createUser(_name:String, _lName:String, _mail:String, _id:String)
    {
        val items = HashMap<String, Any>()
        items.put("nombre", _name)
        items.put("apellido", _lName)
        items.put("correo", _mail)
        items.put("tipo", "usuario")
        items.put("os", "android")
        items.put("estado", 1)
        items.put("calificacion", 5)

        db.collection("usuarios").document(_id).set(items).addOnSuccessListener {
            void: Void? ->
            val i = Intent(this, PreHomeActivity::class.java)
            startActivity(i)
            finish()
        }.addOnFailureListener{
            println("error")
        }
    }
}
