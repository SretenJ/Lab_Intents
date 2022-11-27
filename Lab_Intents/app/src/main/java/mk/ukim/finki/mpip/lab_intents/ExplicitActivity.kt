package mk.ukim.finki.mpip.lab_intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ExplicitActivity : AppCompatActivity() {
    private lateinit var editText: EditText

    private lateinit var buttonCancel: Button

    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_explicit)

        editText = findViewById(R.id.editTEXT)

        buttonCancel = findViewById(R.id.buttonCancel)

        buttonSubmit = findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener{ _ ->
            Intent().let { intent ->
                intent.putExtra("input", editText.text)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

        buttonCancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}