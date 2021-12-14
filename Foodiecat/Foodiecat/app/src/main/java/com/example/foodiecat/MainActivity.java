package com.example.foodiecat;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.Bundle;
import android.os.Build;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

import com.example.foodiecat.databinding.ActivityMainBinding;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_HTTP_URL_CONNECTION = "HTTP_URL_CONNECTION";
    // Child thread sent message type value to activity main thread Handler.
    private static final int REQUEST_CODE_SHOW_RESPONSE_TEXT = 1;
    // The key of message stored server returned data.
    private static final String KEY_RESPONSE_TEXT = "KEY_RESPONSE_TEXT";
    // Request method GET. The value must be uppercase.
    private static final String REQUEST_METHOD_GET = "GET";
    // Request web page url input text box.
//    private EditText requestUrlEditor = null;
    // Send http request button.
    private Button requestUrlButton = null;
    // TextView to display server returned page html text.
    private TextView responseTextView = null;
    // This handler used to listen to child thread show return page html text message and display those text in responseTextView.
    private Handler uiUpdater = null;
    static boolean SUCCESS_FLAG = true;
    private ActivityMainBinding binding;
    Button getBtn;
    Button sendBtn;
    TextView getText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
//        initControls();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        getBtn = findViewById(R.id.getbutton);
        sendBtn = findViewById(R.id.sendbutton);
        getText = findViewById(R.id.textView);

        getBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                HttpURLConnection httpConn = null;
//                // Read text input stream.
//                InputStreamReader isReader = null;
//                // Read text into buffer.
//                BufferedReader bufReader = null;
//                // Save server response text.
//                StringBuffer readTextBuf = new StringBuffer();
//                try {
//                    // Create a URL object use page url.
//                    URL url = new URL("http://3.133.112.5:8080/");
//                    // Open http connection to web server.
//                    httpConn = (HttpURLConnection)url.openConnection();
//                    // Set http request method to get.
//                    httpConn.setRequestMethod(REQUEST_METHOD_GET);
//                    // Set connection timeout and read timeout value.
//                    httpConn.setConnectTimeout(10000);
//                    httpConn.setReadTimeout(10000);
//                    // Get input stream from web url connection.
//                    InputStream inputStream = httpConn.getInputStream();
//                    // Create input stream reader based on url connection input stream.
//                    isReader = new InputStreamReader(inputStream);
//                    // Create buffered reader.
//                    bufReader = new BufferedReader(isReader);
//                    // Read line of text from server response.
//                    String line = bufReader.readLine();
//                    // Loop while return line is not null.
//                    while(line != null)
//                    {
//                        // Append the text to string buffer.
//                        readTextBuf.append(line);
//                        // Continue to read text line.
//                        line = bufReader.readLine();
//                    }
//                    // Send message to main thread to update response text in TextView after read all.
//                    Message message = new Message();
//                    // Set message type.
//                    message.what = REQUEST_CODE_SHOW_RESPONSE_TEXT;
//                    // Create a bundle object.
//                    Bundle bundle = new Bundle();
//                    // Put response text in the bundle with the special key.
//                    bundle.putString(KEY_RESPONSE_TEXT, readTextBuf.toString());
//                    // Set bundle data in message.
//                    message.setData(bundle);
//                    // Send message to main thread Handler to process.
//                    uiUpdater.sendMessage(message);
//                }catch(MalformedURLException ex)
//                {
//                    Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
//                }catch(IOException ex)
//                {
//                    Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
//                }finally {
//                    try {
//                        if (bufReader != null) {
//                            bufReader.close();
//                            bufReader = null;
//                        }
//                        if (isReader != null) {
//                            isReader.close();
//                            isReader = null;
//                        }
//                        if (httpConn != null) {
//                            httpConn.disconnect();
//                            httpConn = null;
//                        }
//                    }catch (IOException ex)
//                    {
//                        Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
//                    }
//                }
                try {
                    URL url = new URL("http://3.133.112.5:8080/");
//                    String result = null;
                    System.out.println("here");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream input = conn.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(input));
                    String line = null;
                    System.out.println(conn.getResponseCode());
                    StringBuffer sb = new StringBuffer();
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    System.out.println(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
//                    conn.setConnectTimeout(5000);
//                    conn.setReadTimeout(5000);
//                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//                    conn.setRequestProperty("Accept", "application/json");
//                    conn.setRequestMethod("GET");
//                    conn.setDoOutput(true);
//                    conn.setDoInput(true);
//                    conn.connect();
//                    if (conn.getResponseCode() == 200){
//                        InputStream inputStream = new BufferedInputStream(conn.getInputStream());
//                        result=convertResultToString(inputStream);
//                        getText.setText(result);
//                        System.out.println(result);
//                        conn.disconnect();
//                    }


//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mainwriter("Android posting");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
//    private void initControls()
//    {
////        if(requestUrlEditor == null)
////        {
////            requestUrlEditor = (EditText)findViewById(R.id.http_url_editor);
////        }
//        if(sendBtn == null)
//        {
//            sendBtn = (Button)findViewById(R.id.sendbutton);
//        }
//        if(getText == null)
//        {
//            getText = (TextView)findViewById(R.id.textView);
//        }
//        // This handler is used to wait for child thread message to update server response text in TextView.
//        if(uiUpdater == null)
//        {
//            uiUpdater = new Handler()
//            {
//                @Override
//                public void handleMessage(Message msg) {
//                    if(msg.what == REQUEST_CODE_SHOW_RESPONSE_TEXT)
//                    {
//                        Bundle bundle = msg.getData();
//                        if(bundle != null)
//                        {
//                            String responseText = bundle.getString(KEY_RESPONSE_TEXT);
//                            responseTextView.setText(responseText);
//                        }
//                    }
//                }
//            };
//        }
//    }
    private String convertResultToString(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String li;

        while (true){
            try {
                if(!((li = bufferedReader.readLine()) != null)){
                    stringBuilder.append('\n');
                }
            } catch(IOException e){
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stringBuilder.toString();
        }
    }

    public static boolean mainwriter (String data) throws IOException {
        Thread thread = new Thread(new Runnable() {
            public boolean SUCCESS_FLAG;
            @Override
            public void run() {
                try {
                    URL url = new URL("http://3.133.112.5:8080/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("MSG", data);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();
                    SUCCESS_FLAG = conn.getResponseCode() == 200;
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return SUCCESS_FLAG;
    }
}

