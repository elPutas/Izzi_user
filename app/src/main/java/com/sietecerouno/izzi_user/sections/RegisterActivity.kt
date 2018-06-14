package com.sietecerouno.izzi_user.sections

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import java.util.regex.Pattern

class RegisterActivity : BaseActivity()
{

    var fbAuth = FirebaseAuth.getInstance()
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Registro")
        supportActionBar?.setElevation(0.0f)

        db = FirebaseFirestore.getInstance()

        val btnSend = findViewById<TextView>(R.id.btn_next) as TextView
        btnSend.setOnClickListener{view ->
            checkFields()
        }
    }

    private fun checkFields()
    {
        val name = findViewById<EditText>(R.id.name) as EditText
        val lName = findViewById<EditText>(R.id.lastName) as EditText
        val mail = findViewById<EditText>(R.id.mail) as EditText
        val pass = findViewById<EditText>(R.id.pass) as EditText
        val passR = findViewById<EditText>(R.id.passR) as EditText

        if(name.text.toString() != "")
        {
            if(lName.text.toString() != "")
            {
                if(mail.text.toString() != "" && isEmailValid(mail.text.toString()))
                {
                    if(pass.text.toString() != "" && pass.text.toString().length > 6)
                    {
                        if(pass.text.toString() == passR.text.toString())
                        {
                            authUser(mail.text.toString(), pass.text.toString(), name.text.toString(), lName.text.toString())
                        }else{
                            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(this, "Falta tu contraseña", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "Falta tu correo o tiene formato inválido", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Falta tu apellido", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Falta tu nombre", Toast.LENGTH_SHORT).show()
        }

    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    private fun authUser(_mail:String, _pass:String, _name:String, _lName:String)
    {
        fbAuth.createUserWithEmailAndPassword(_mail, _pass)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful)
            {
                createUser(_name, _lName, _mail, fbAuth.uid!!)
            }else{
                Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun createUser(_name: String, _lName: String, _mail: String, _id: String)
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


    //back btn
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
