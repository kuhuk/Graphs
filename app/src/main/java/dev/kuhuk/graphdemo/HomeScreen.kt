package dev.kuhuk.graphdemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button

class HomeScreen : DemoBase() {
    override fun saveToGallery() {
        // Intentionally kept empty
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_home_screen)
        title = "MultipleCharts"

        val btnLineChart : Button? = findViewById(R.id.btnLineChart)
        val btnBarChart : Button? = findViewById(R.id.btnBarChart)
        val btnPieChart : Button? = findViewById(R.id.btnPieChart)

        btnLineChart!!.setOnClickListener {
            val intent = Intent(this, LineChartActivity::class.java)
            startActivity(intent)
        }

        btnBarChart!!.setOnClickListener {
            val intent = Intent(this, BarChartActivity::class.java)
            startActivity(intent)
        }

        btnPieChart!!.setOnClickListener {
            val intent = Intent(this, PieChartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.only_github, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.viewGithub -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/ListViewMultiChartActivity.java")
                startActivity(i)
            }
        }
        return true
    }
}
