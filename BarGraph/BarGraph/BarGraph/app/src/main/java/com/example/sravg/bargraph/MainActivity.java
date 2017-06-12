package com.example.sravg.bargraph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = (BarChart)findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(88f,1));
        barEntries.add(new BarEntry(66f,2));
        barEntries.add(new BarEntry(12f,3));
        barEntries.add(new BarEntry(19f,4));
        barEntries.add(new BarEntry(91f,5));
        barEntries.add(new BarEntry(25f,6));
        barEntries.add(new BarEntry(36f,7));
        barEntries.add(new BarEntry(45f,8));
        barEntries.add(new BarEntry(72f,9));
        barEntries.add(new BarEntry(45f,10));
        barEntries.add(new BarEntry(80f,11));


        //barEntries.add(new BarEntry(55f,6));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        ArrayList<String> dates = new ArrayList<>();
        dates.add("Jan");
        dates.add("Feb");
        dates.add("Mar");
        dates.add("Apr");
        dates.add("May");
        dates.add("Jun");
        dates.add("July");
        dates.add("Aug");
        dates.add("Sept");
        dates.add("Oct");
        dates.add("Nov");
        dates.add("Dec");



        BarData data = new BarData(dates, barDataSet);
        barChart.setData(data);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        barChart.setDoubleTapToZoomEnabled(true);
        barChart.setDescription("Expenses");
        barChart.setDescriptionTextSize(6f);
        barChart.setDescriptionColor(ColorTemplate.COLOR_NONE);

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }
}
