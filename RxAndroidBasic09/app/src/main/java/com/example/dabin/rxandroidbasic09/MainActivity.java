package com.example.dabin.rxandroidbasic09;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dabin.rxandroidbasic09.domain.Data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    //http://api.openweathermap.org
    static final String BASEURL = " http://api.openweathermap.org";
    static final String APIKEY = "16abf893051abf87955994a46972d74d";
    Button btn_get_wheather;
    TextView tv_result;
    EditText et_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_get_wheather = (Button) findViewById(R.id.btn_get_weather);
        btn_get_wheather.setOnClickListener(e -> getWheather());
        et_city = (EditText) findViewById(R.id.et_city);


    }

    private void getWheather() {
        String cityName = et_city.getText().toString();

        // 1. Retrofit 클라이언트 생성
        Retrofit client  = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //2. Rest API 서비스 생성
        Iweather service = client.create(Iweather.class);

        //3. 데이터 Observable 생성
        Observable<Data> weatherData = service.getData(cityName,APIKEY);

        //4.  가. subscribeON - 데이터를 가져오는 대상은 newThread라는 새로운 Thread에서 작업한다.
        //    나. 화면에 세팅하는 옵져버 : mainThread 에서 작업한다.
        weatherData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            String temp = "id = " + data.getId() + ", name = " + data.getName() + ", visibility = " + data.getVisibility();
                            tv_result.setText(temp);
                        }
                );
    }
}
