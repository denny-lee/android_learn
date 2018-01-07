package liwei.hackcode.web;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import liwei.hackcode.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "OkHttpActivity";

    TextView resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_ok);

        Button btn = (Button) findViewById(R.id.send_req1);
        resp = (TextView) findViewById(R.id.resp_text1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_req1) {
            sendReq();
        }
    }

    private void sendReq() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "begin req........");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    Response response = client.newCall(request).execute();
                    String respText = response.body().string();
                    showResp(respText);
                } catch (Exception e) {
                    Log.e(TAG, "exception");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResp(final String s) {
        Log.d(TAG, "begin show....." + s);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "main ui thread." + s);
                resp.setText(s);

            }
        });
    }
}
