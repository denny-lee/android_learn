package liwei.hackcode.web;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import liwei.hackcode.R;
import liwei.hackcode.persistent.MyDatabaseHelper;

public class UrlConnActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UrlConnActivity";

    TextView resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_url);

        Button btn = (Button) findViewById(R.id.send_req);
        resp = (TextView) findViewById(R.id.resp_text);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_req) {
            sendReq();
        }
    }

    private void sendReq() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                BufferedReader br = null;
                try {
                    Log.d(TAG, "begin req........");
                    URL url = new URL("http://www.baidu.com");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(15000);
                    conn.setReadTimeout(15000);
                    InputStream in = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    // 这里总是得不到返回，main方法执行同样代码可以得到返回
                    while ((line = br.readLine()) != null) {
                        Log.d(TAG, "lineq........"+line);
                        sb.append(line);
                    }
                    showResp(sb.toString());
                } catch (Exception e) {
                    Log.e(TAG, "exception");
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
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
