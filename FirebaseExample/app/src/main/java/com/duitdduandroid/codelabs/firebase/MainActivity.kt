package com.duitdduandroid.codelabs.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.duitdduandroid.codelabs.firebase.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private var googleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnGoogle.setOnClickListener { signInWithGoogle() }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun signInWithGoogle() {
        googleSignInClient?.signInIntent?.run {
            startActivityForResult(this, REQ_CODE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SIGN_IN) {
            val signInTask = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = signInTask.getResult(ApiException::class.java)
                onGoogleSignInAccount(account)
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

    private fun onGoogleSignInAccount(account: GoogleSignInAccount?) {
        if (account != null) {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            firebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener {
                onFirebaseAuthTask(it)
            }
        }
    }

    private fun onFirebaseAuthTask(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            // Google로 로그인 성공
            Log.d(this::class.java.simpleName, "User Email :: ${task.result?.user?.email}")
        } else {
            // Google로 로그인 실패
            Log.d(this::class.java.simpleName, "Firebase Login Failure.")
        }
    }

    companion object {
        private const val REQ_CODE_SIGN_IN = 1000
    }
}