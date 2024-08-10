package com.example.expensetracker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.Menu
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.AttrRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var hideSystemBarsRunnable: Runnable
    private lateinit var pieChart: PieChart
    private val months = arrayOf("January", "February", "March", "April")
    private val salary = intArrayOf(16000, 20000, 30000, 50000)

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        handler = Handler(Looper.getMainLooper())
        hideSystemBarsRunnable = Runnable {
            window.decorView.windowInsetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            handler.postDelayed(hideSystemBarsRunnable, 5000) // Re-run every 5 seconds
        }

        handler.post(hideSystemBarsRunnable)

        pieChart = findViewById(R.id.pieChart)
        setupChartView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(hideSystemBarsRunnable)
    }

    private fun setupChartView() {
        val entries = ArrayList<PieEntry>()
        for (i in months.indices) {
            entries.add(PieEntry(salary[i].toFloat(), months[i]))
        }

        val dataSet = PieDataSet(entries, "Salary Data")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        val data = PieData(dataSet)

        data.setValueTextColor(Color.parseColor("#266DD9"))
        data.setValueTextSize(12f)

        pieChart.data = data

        pieChart.description.isEnabled = false
        pieChart.setUsePercentValues(true)

        pieChart.setEntryLabelColor(Color.parseColor("#266DD9"))
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setCenterTextColor(Color.parseColor("#266DD9"))
        pieChart.setCenterText("Salary")
        pieChart.setCenterTextSize(20f)

        // Customize legend text color
        pieChart.legend.textColor = Color.parseColor("#266DD9")
        pieChart.legend.textSize = 12f

        pieChart.animateY(1000)

        setChartBackgroundBasedOnTheme(pieChart, this)
        pieChart.invalidate()
    }

    private fun setChartBackgroundBasedOnTheme(chart: PieChart, context: Context) {
        @AttrRes val backgroundAttr = android.R.attr.colorBackground
        val typedValue = TypedValue()
        context.theme.resolveAttribute(backgroundAttr, typedValue, true)
        val backgroundColor = typedValue.data
        chart.setBackgroundColor(backgroundColor)
    }
}