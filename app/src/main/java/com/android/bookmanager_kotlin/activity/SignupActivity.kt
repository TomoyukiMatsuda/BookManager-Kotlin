package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.AlertDialogUtils.showAlertDialog
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.android.bookmanager_kotlin.util.ValidationUtils

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setTitle(R.string.app_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // アクションバー保存ボタンを表示
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    // アクションバーボタンクリック処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

                return true
            }
            R.id.bt_save -> {
                val email = findViewById<EditText>(R.id.input_email).text.toString()
                val password = findViewById<EditText>(R.id.input_password).text.toString()
                val passwordConfirmation = findViewById<EditText>(R.id.input_password_confirmation).text.toString()

                @StringRes
                val errorMessage = ValidationUtils.validationCheckSignup(email, password, passwordConfirmation)

                if (errorMessage == null) {
                    startActivity(Intent(this, BookListActivity::class.java))
                    finish()
                } else {
                    showAlertDialog(R.string.dialog_title, errorMessage)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 背景タップでキーボードを非表示
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val focusView = currentFocus ?: return false
        KeyboardUtils.hideKeyboard(focusView)

        return false
    }
}
