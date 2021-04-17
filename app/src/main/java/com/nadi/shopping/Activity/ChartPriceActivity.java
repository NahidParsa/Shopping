package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.PriceChartModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartPriceActivity extends AppCompatActivity {

    //api
    ApiInterface apiInterface;

    // bundle
    Bundle bundle;
    String id;

    // general
    LineChart lineChartPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_price);

        init();
        
    }

    private void init() {
        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        bundle = getIntent().getExtras();
        id = bundle.getString(KEY.id);


        lineChartPrice = findViewById(R.id.lineChartPrice_chartPriceActivity);

        apiInterface.postIdGetPriceChart(id).enqueue(new Callback<List<PriceChartModel>>() {
            List<Entry> values = new ArrayList<>();
            @Override
            public void onResponse(Call<List<PriceChartModel>> call, Response<List<PriceChartModel>> response) {

                Log.d("price chart", "onResponse: " + response.body());

                for (int i = 0; i < response.body().size(); i++) {

                    values.add(new Entry(  i, Integer.parseInt(response.body().get(i).getPrice()) ) );

                    LineDataSet lineDataSet = new LineDataSet(values , "Price");
                    lineDataSet.setDrawFilled(true);
                    lineDataSet.setLineWidth(3f);
                    lineDataSet.setFillDrawable(ContextCompat.getDrawable(ChartPriceActivity.this , R.color.colorBlue));

                    List<ILineDataSet> iLineDataSets = new ArrayList<>();
                    iLineDataSets.add(lineDataSet);

                    LineData lineData = new LineData(iLineDataSets);

                    lineChartPrice.setData(lineData);
                    lineChartPrice.animateXY(500, 500);

                    XAxis xAxis = lineChartPrice.getXAxis();
                    xAxis.setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            xAxis.setLabelCount(response.body().size(), true);
                            return response.body().get((int)value).getMunth();
                        }
                    });

                }
            }
            @Override
            public void onFailure(Call<List<PriceChartModel>> call, Throwable t) {

            }
        });

    }
}
