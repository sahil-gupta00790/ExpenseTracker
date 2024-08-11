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
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.AttrRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.expensetracker.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var hideSystemBarsRunnable: Runnable

    private val months = arrayOf("January", "February", "March", "April")
    private val salary = intArrayOf(16000, 20000, 30000, 50000)

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this , R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        handler = Handler(Looper.getMainLooper())
        hideSystemBarsRunnable = Runnable {
            window.decorView.windowInsetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            handler.postDelayed(hideSystemBarsRunnable, 5000) // Re-run every 5 seconds
        }

        handler.post(hideSystemBarsRunnable)


        setupChartView()
        binding.chartToggle.setOnCheckedChangeListener{_,isChecked->
            if (isChecked){
                binding.pieChart.visibility=View.GONE
                binding.barChart.visibility=View.VISIBLE
                setupBarChartView()
            }
            else{
                binding.pieChart.visibility=View.VISIBLE
                binding.barChart.visibility=View.GONE
                setupChartView()
            }
        }

        binding.recyclerView.setBackgroundColor(Color.CYAN)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=MyRecyclerViewAdapter()


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

        binding.pieChart.data = data
        binding.pieChart.description.isEnabled = false
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.setEntryLabelColor(Color.parseColor("#266DD9"))
        binding.pieChart.setEntryLabelTextSize(12f)
        binding.pieChart.setCenterTextColor(Color.parseColor("#266DD9"))
        binding.pieChart.setCenterText("Salary")
        binding.pieChart.setCenterTextSize(20f)

        // Customize legend text color
        binding.pieChart.legend.textColor = Color.parseColor("#266DD9")
        binding.pieChart.legend.textSize = 12f
        binding.pieChart.animateY(1000)

        setChartBackgroundBasedOnTheme(binding.pieChart, this)
        binding.pieChart.invalidate()
    }

    private fun setChartBackgroundBasedOnTheme(chart: PieChart, context: Context) {
        @AttrRes val backgroundAttr = android.R.attr.colorBackground
        val typedValue = TypedValue()
        context.theme.resolveAttribute(backgroundAttr, typedValue, true)
        val backgroundColor = typedValue.data
        chart.setBackgroundColor(backgroundColor)
    }
    private fun setupBarChartView() {
        val entries = ArrayList<BarEntry>()
        for (i in months.indices) {
            entries.add(BarEntry(i.toFloat(), salary[i].toFloat()))
        }

        val dataSet = BarDataSet(entries, "Salary Data")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        val data = BarData(dataSet)

        data.setValueTextColor(Color.parseColor("#266DD9"))
        data.setValueTextSize(12f)

        binding.barChart.data = data

        binding.barChart.description.isEnabled = false
        binding.barChart.setFitBars(true)

        // X-axis customization
        val xAxis = binding.barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(months)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.parseColor("#266DD9")
        xAxis.textSize = 12f
        xAxis.setDrawGridLines(false)

        // Y-axis customization
        val leftAxis = binding.barChart.axisLeft
        leftAxis.textColor = Color.parseColor("#266DD9")
        leftAxis.textSize = 12f
        leftAxis.setDrawGridLines(false)

        // Disable right axis
        binding.barChart.axisRight.isEnabled = false

        // Customize legend text color
        binding.barChart.legend.textColor = Color.parseColor("#266DD9")
        binding.barChart.legend.textSize = 12f

        binding.barChart.animateY(1000)

        setBarChartBackgroundBasedOnTheme(binding.barChart, this)
        binding.barChart.invalidate()
    }

    private fun setBarChartBackgroundBasedOnTheme(chart: BarChart, context: Context) {
        @AttrRes val backgroundAttr = android.R.attr.colorBackground
        val typedValue = TypedValue()
        context.theme.resolveAttribute(backgroundAttr, typedValue, true)
        val backgroundColor = typedValue.data
        chart.setBackgroundColor(backgroundColor)
    }
}