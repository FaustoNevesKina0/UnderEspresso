package br.com.fausto.underespressoapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.fausto.underespressoapp.tools.EspressoCounter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_dialog.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var context = this
    }

    fun openToast(view: View) {
        Toast.makeText(this, "This is a Toast message!", Toast.LENGTH_SHORT).show()
    }

    fun dialogAlert(view: View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.btnClose.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    fun openActivity(view: View) {
        EspressoCounter.increment()
        var intent: Intent = Intent(this, SecondaryActivity::class.java)
        intent.putExtra("sentText", textToSend.text.toString())
        EspressoCounter.decrement()
        startActivity(intent)
    }
}