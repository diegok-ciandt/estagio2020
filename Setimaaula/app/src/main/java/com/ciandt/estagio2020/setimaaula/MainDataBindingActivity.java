package com.ciandt.estagio2020.setimaaula;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainDataBindingActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private int qtdClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_main);
        binding.setValor("TEXTO DE TESTE");
        binding.setQtd(qtdClick);
        binding.setUser(new User());

        binding.editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.editEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //enableButton();
            }
        });


        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
                qtdClick += 1;
                binding.setQtd(qtdClick);
            }
        });
    }


    private void enableButton() {
        if (binding.editEmail.getText().length() > 0 &&
                binding.editPassword.getText().length() > 0) {
            binding.buttonLogin.setEnabled(true);
        } else {
            binding.buttonLogin.setEnabled(false);
        }
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Invalid password or email!");
        dialogBuilder.setTitle("Login error");
        dialogBuilder.setIcon(R.drawable.ic_alert);
        dialogBuilder.setNegativeButton("OK", null);
        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }

    private void sendMessage() {
        loading(true);
        Log.d("tag", "Step 1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("tag", "Step 2");
                callAPI();
                Log.d("tag", "Step 3");
            }
        }).start();
        Log.d("tag", "Step 4");
    }

    private void callbackAPI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading(false);
                showDialog();
            }
        });
    }

    private void callAPI() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callbackAPI();
    }

    private void loading(boolean isLoading)  {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.progressBack.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.progressBack.setVisibility(View.GONE);
        }
    }
}
