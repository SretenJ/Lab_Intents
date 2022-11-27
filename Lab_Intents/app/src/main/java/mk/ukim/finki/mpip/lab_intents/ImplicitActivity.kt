package mk.ukim.finki.mpip.lab_intents

import ViewAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class ImplicitActivity : AppCompatActivity() {
    private lateinit var TextViewList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        TextViewList = findViewById(R.id.listView)

        TextViewList.adapter = ViewAdapter(loadData())
        }
    @SuppressLint("QueryPermissionsNeeded")
    private fun loadData():MutableList<String> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        
        val list = packageManager.queryIntentActivities(intent, 0)
        
        val mainList = mutableListOf("MainActivity")

        list.forEach {
            e->mainList.add(e.activityInfo.name)
        }

        return mainList
    }
}