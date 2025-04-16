package com.quadris.riskybusiness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.quadris.riskybusiness.databinding.ActivityLoginBinding
import com.quadris.riskybusiness.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var tvRegister: TextView? = null
        var btnLogin: Button? = null
        var etLoginEmail: EditText? = null
        var etLoginPassword: EditText? = null


        tvRegister = findViewById(R.id.tv_register)
        btnLogin = findViewById(R.id.btn_login)
        etLoginEmail = findViewById(R.id.et_login_email)
        etLoginPassword = findViewById(R.id.et_login_password)


        //adding functionality to register text view
        tvRegister.setOnClickListener {
            //move to register page
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        //functionality to login button
        btnLogin.setOnClickListener { //knows when register button is clicked
            when { /*if the email slot is empty when register button is clicked it will bring
                    up a small notification asking for email */
                TextUtils.isEmpty(
                    etLoginEmail.text.toString()
                        .trim { it <= ' ' }) -> { /*trim gets rid of any spaces if user
                        accidentally adds them */
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                /*if the email slot is empty when register button is clicked it will bring up
                 a small notification asking for email */
                TextUtils.isEmpty(
                    etLoginPassword.text.toString()
                        .trim { it <= ' ' }) -> { /* trim gets rid of any spaces if user
                        accidentally adds them */
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter password", //toast means a small in app notification
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = etLoginEmail.text.toString()
                        .trim { it <= ' ' } //creates variable to hold inputted email
                    val password: String = etLoginPassword.text.toString()
                        .trim { it <= ' ' }//creates variable to hold inputted password
                    authenticate(email, password)


                }
            }
        }
    }

    fun authenticate(email: String, password: String){
//creates an instance and creates a register a user with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                //If the registration is successfully done
                if (task.isSuccessful) {

                    Toast.makeText( /*makes notification to let user know their
                                 account was made */
                        this@LoginActivity,
                        "You are logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    //send us to main activity
                    val intent =
                        Intent(this@LoginActivity, MainActivity::class.java)
                    /*closes down activities running in the background if user is
                    switching between them*/
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra(
                        "user_id",
                        FirebaseAuth.getInstance().currentUser!!.uid
                    )
                    intent.putExtra("email_id", email)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()

                } else {

                    //if login isn't successful show error message
                    Toast.makeText(
                        this@LoginActivity,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

}