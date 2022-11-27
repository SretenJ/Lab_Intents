package mk.ukim.finki.mpip.lab_intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        var resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    textView.text = data?.getStringExtra("input")
                }
            }
        textView.setText(intent.getStringExtra("input"))

        val explicitButton = findViewById<Button>(R.id.startExplicitButton)

        explicitButton.setOnClickListener {
            val explicitIntent = Intent(this, ExplicitActivity::class.java)
            explicitIntent.putExtra("input", textView.text)
            resultLauncher.launch(explicitIntent)
        }

        val implicitButton = findViewById<Button>(R.id.startImplicitButton)

        implicitButton.setOnClickListener {
            val intent = Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
            }
            startActivity(intent);
        }

        val shareButton = findViewById<Button>(R.id.startShareButton)

        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).let { shareIntent ->
                shareIntent.putExtra(Intent.EXTRA_TITLE, "MPiP Send Title")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                startActivity(shareIntent)
            }
        }

        val photoButton = findViewById<Button>(R.id.startSelectPhotoButton)

        photoButton.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivity(Intent.createChooser(intent, "Select Image from device"))
        }

    }
}