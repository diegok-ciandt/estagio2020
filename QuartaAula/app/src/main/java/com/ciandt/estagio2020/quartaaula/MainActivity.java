package com.ciandt.estagio2020.quartaaula;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.ciandt.estagio2020.quartaaula.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final int RESULT_DATA_SCREEN = 100120;

    private OperationVolley operationVolley = new OperationVolley();

    private Response.Listener<String> successListener;

    private Response.ErrorListener errorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        operationVolley.setup(this);
        setupListener();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding != null) {
            loadData();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    public void goToNextScreen(View v) {
        String strTitle = binding.editText.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("TEXT_TITLE", strTitle);
        intent.putExtra("TEXT_LONG", binding.textViewResult.getText().toString());
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String strResult = data.getStringExtra("DATA_RESULT");
        binding.textViewResult.setText(strResult);
    }

    private void saveData() {
            SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("SWITCH", binding.switch1.isChecked());
            if (binding.switch1.isChecked()) {
                editor.putString("TITLE", binding.editText.getText().toString());
                editor.putString("MESSAGE", binding.textViewResult.getText().toString());
            }
            editor.apply();

    }

    private void loadData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPref.getBoolean("SWITCH", false);
        String title = sharedPref.getString("TITLE", "");
        String message = sharedPref.getString("MESSAGE", "");
        binding.switch1.setChecked(isChecked);
        binding.editText.setText(title);
        if (binding.textViewResult.getText().toString().isEmpty()) {
            binding.textViewResult.setText(message);
        }

    }

    public void requestData(View v) {
        loadImage();

        new Thread(new Runnable() {
            @Override
            public void run() {
                operationVolley.request("https://www.google.com",
                        successListener, errorListener);
            }
        }).start();

    }

    private void setupListener() {
        successListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: SUCESSO");
                writeResponse(response);
            }
        };

        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onResponse: FALHA");
            }
        };
    }

    private void writeResponse(final String strResponse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.textViewLoadData.setText(strResponse);
            }
        });
    }

    private void loadImage() {
        int rand = new Random().nextInt(500)+100;
        String urlImg = "https://www.internetvibes.net/wp-content/gallery/avatars/"+rand+".png";
        Glide.with(this).load(urlImg).into(binding.imageView);
    }
}
