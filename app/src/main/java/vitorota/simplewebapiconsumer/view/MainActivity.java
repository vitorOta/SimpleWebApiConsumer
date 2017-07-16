package vitorota.simplewebapiconsumer.view;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import vitorota.simplewebapiconsumer.R;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgMethod;
    private EditText etUrl;
    private ProgressBar pbLoading;
    private Button bRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgMethod = (RadioGroup)findViewById(R.id.rgMethod);
        etUrl = (EditText) findViewById(R.id.etUrl);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        bRequest = (Button) findViewById(R.id.bRequest);
    }

    protected void makeRequest(View v){
        bRequest.setText("Loading...");
        bRequest.setActivated(false);
        pbLoading.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = etUrl.getText().toString();
        int method= Request.Method.GET;

//        switch (rgMethod.getCheckedRadioButtonId()){
//            case R.id.rbGet:
//                method= Request.Method.GET;
//                break;
//            case R.id.rbPost:
//                method= Request.Method.POST;
//                break;
//        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Response");

        StringRequest req =new StringRequest(
                method,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setMessage(response);
                        builder.show();
                        pbLoading.setVisibility(View.INVISIBLE);
                        bRequest.setText("Request");
                        bRequest.setActivated(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        builder.setMessage("Erro !\n" + error.getMessage());
                        //sim, eu poderia ter criado uma função ao invés de repetir o código
                        builder.show();
                        pbLoading.setVisibility(View.INVISIBLE);
                        bRequest.setText("Request");
                        bRequest.setActivated(true);
                    }
                });
        queue.add(req);
    }
}
