package com.example.dabin.remote_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
    open Api Key :634f617053776f77313038524b766c44
    사용 API :http://openapi.seoul.go.kr:8088/(인증키)/json/JobFairInfo/1/5/2016
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String key = "634f617053776f77313038524b766c44";

        String serviceName = "JobFairInfo";
        int begin = 1;
        int end = 5;
        final TextView textView = (TextView)findViewById(R.id.text);



        //String url = "http://openapi.seoul.go.kr:8088/" + key + "/json/" + serviceName + "/" + begin + "/" + end;
        // 1. Retrofit client 생성
        Retrofit client = new Retrofit.Builder().baseUrl("http://openapi.seoul.go.kr:8088")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 2. Retrofit client에서 사용할 interface
        IJobFairOpenData service = client.create(IJobFairOpenData.class);
        // 3. interface(서비스)를 통해서 데이터를 호출
        Call<RemoteData> remoteData = service.getData(key, serviceName, begin, end);
        // 4. 비동기 데이터를 받기 위한 리스너 세팅
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if (response.isSuccessful()) {
                    RemoteData data = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (RemoteData.JobFairInfo.Row row : data.getJobFairInfo().getRow()) {
                        Log.i("tag",row.getJOBFAIR_DATE());
                        sb.append(row.getJOBFAIR_NAME() +" \n ");
                    }
                    textView.setText(sb.toString());
                } else {
                    Log.e("RemoteData", response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

interface IJobFairOpenData {
    @GET("/{key}/json/{serviceName}/{begin}/{end}/")
    Call<RemoteData> getData(@Path("key") String key, @Path("serviceName") String serviceName, @Path("begin") int begin, @Path("end") int end);
}

class RemoteData {
    private JobFairInfo JobFairInfo;

    public JobFairInfo getJobFairInfo ()
    {
        return JobFairInfo;
    }

    public void setJobFairInfo (JobFairInfo JobFairInfo)
    {
        this.JobFairInfo = JobFairInfo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [JobFairInfo = "+JobFairInfo+"]";
    }
    public class JobFairInfo
    {
        private RESULT RESULT;

        private String list_total_count;

        private Row[] row;

        public RESULT getRESULT ()
        {
            return RESULT;
        }

        public void setRESULT (RESULT RESULT)
        {
            this.RESULT = RESULT;
        }

        public String getList_total_count ()
        {
            return list_total_count;
        }

        public void setList_total_count (String list_total_count)
        {
            this.list_total_count = list_total_count;
        }

        public Row[] getRow ()
        {
            return row;
        }

        public void setRow (Row[] row)
        {
            this.row = row;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
        }
        public class Row
        {
            private String JOBFAIR_JOINT_INSTT_SN;

            private String JOBFAIR_FRTIME;

            private String JOBFAIR_JOINT_INSTT_FLAG;

            private String JOBFAIR_TURN;

            private String JOBFAIR_DATE;

            private String JOBFAIR_LOCATION;

            private String ACTIVATE_FLAG;

            private String GUIDE_INTRO;

            private String JOBFAIR_JOINT_AUSPICES;

            private String JOBFAIR_NAME;

            private String JOBFAIR_EDTIME;

            private String GUIDE_IMG;

            private String JOBFAIR_URL;

            private String JOBFAIR_YEAR;

            public String getJOBFAIR_JOINT_INSTT_SN ()
            {
                return JOBFAIR_JOINT_INSTT_SN;
            }

            public void setJOBFAIR_JOINT_INSTT_SN (String JOBFAIR_JOINT_INSTT_SN)
            {
                this.JOBFAIR_JOINT_INSTT_SN = JOBFAIR_JOINT_INSTT_SN;
            }

            public String getJOBFAIR_FRTIME ()
            {
                return JOBFAIR_FRTIME;
            }

            public void setJOBFAIR_FRTIME (String JOBFAIR_FRTIME)
            {
                this.JOBFAIR_FRTIME = JOBFAIR_FRTIME;
            }

            public String getJOBFAIR_JOINT_INSTT_FLAG ()
            {
                return JOBFAIR_JOINT_INSTT_FLAG;
            }

            public void setJOBFAIR_JOINT_INSTT_FLAG (String JOBFAIR_JOINT_INSTT_FLAG)
            {
                this.JOBFAIR_JOINT_INSTT_FLAG = JOBFAIR_JOINT_INSTT_FLAG;
            }

            public String getJOBFAIR_TURN ()
            {
                return JOBFAIR_TURN;
            }

            public void setJOBFAIR_TURN (String JOBFAIR_TURN)
            {
                this.JOBFAIR_TURN = JOBFAIR_TURN;
            }

            public String getJOBFAIR_DATE ()
            {
                return JOBFAIR_DATE;
            }

            public void setJOBFAIR_DATE (String JOBFAIR_DATE)
            {
                this.JOBFAIR_DATE = JOBFAIR_DATE;
            }

            public String getJOBFAIR_LOCATION ()
            {
                return JOBFAIR_LOCATION;
            }

            public void setJOBFAIR_LOCATION (String JOBFAIR_LOCATION)
            {
                this.JOBFAIR_LOCATION = JOBFAIR_LOCATION;
            }

            public String getACTIVATE_FLAG ()
            {
                return ACTIVATE_FLAG;
            }

            public void setACTIVATE_FLAG (String ACTIVATE_FLAG)
            {
                this.ACTIVATE_FLAG = ACTIVATE_FLAG;
            }

            public String getGUIDE_INTRO ()
            {
                return GUIDE_INTRO;
            }

            public void setGUIDE_INTRO (String GUIDE_INTRO)
            {
                this.GUIDE_INTRO = GUIDE_INTRO;
            }

            public String getJOBFAIR_JOINT_AUSPICES ()
            {
                return JOBFAIR_JOINT_AUSPICES;
            }

            public void setJOBFAIR_JOINT_AUSPICES (String JOBFAIR_JOINT_AUSPICES)
            {
                this.JOBFAIR_JOINT_AUSPICES = JOBFAIR_JOINT_AUSPICES;
            }

            public String getJOBFAIR_NAME ()
            {
                return JOBFAIR_NAME;
            }

            public void setJOBFAIR_NAME (String JOBFAIR_NAME)
            {
                this.JOBFAIR_NAME = JOBFAIR_NAME;
            }

            public String getJOBFAIR_EDTIME ()
            {
                return JOBFAIR_EDTIME;
            }

            public void setJOBFAIR_EDTIME (String JOBFAIR_EDTIME)
            {
                this.JOBFAIR_EDTIME = JOBFAIR_EDTIME;
            }

            public String getGUIDE_IMG ()
            {
                return GUIDE_IMG;
            }

            public void setGUIDE_IMG (String GUIDE_IMG)
            {
                this.GUIDE_IMG = GUIDE_IMG;
            }

            public String getJOBFAIR_URL ()
            {
                return JOBFAIR_URL;
            }

            public void setJOBFAIR_URL (String JOBFAIR_URL)
            {
                this.JOBFAIR_URL = JOBFAIR_URL;
            }

            public String getJOBFAIR_YEAR ()
            {
                return JOBFAIR_YEAR;
            }

            public void setJOBFAIR_YEAR (String JOBFAIR_YEAR)
            {
                this.JOBFAIR_YEAR = JOBFAIR_YEAR;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [JOBFAIR_JOINT_INSTT_SN = "+JOBFAIR_JOINT_INSTT_SN+", JOBFAIR_FRTIME = "+JOBFAIR_FRTIME+", JOBFAIR_JOINT_INSTT_FLAG = "+JOBFAIR_JOINT_INSTT_FLAG+", JOBFAIR_TURN = "+JOBFAIR_TURN+", JOBFAIR_DATE = "+JOBFAIR_DATE+", JOBFAIR_LOCATION = "+JOBFAIR_LOCATION+", ACTIVATE_FLAG = "+ACTIVATE_FLAG+", GUIDE_INTRO = "+GUIDE_INTRO+", JOBFAIR_JOINT_AUSPICES = "+JOBFAIR_JOINT_AUSPICES+", JOBFAIR_NAME = "+JOBFAIR_NAME+", JOBFAIR_EDTIME = "+JOBFAIR_EDTIME+", GUIDE_IMG = "+GUIDE_IMG+", JOBFAIR_URL = "+JOBFAIR_URL+", JOBFAIR_YEAR = "+JOBFAIR_YEAR+"]";
            }
        }

        public class RESULT
        {
            private String MESSAGE;

            private String CODE;

            public String getMESSAGE ()
            {
                return MESSAGE;
            }

            public void setMESSAGE (String MESSAGE)
            {
                this.MESSAGE = MESSAGE;
            }

            public String getCODE ()
            {
                return CODE;
            }

            public void setCODE (String CODE)
            {
                this.CODE = CODE;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [MESSAGE = "+MESSAGE+", CODE = "+CODE+"]";
            }
        }
    }
}