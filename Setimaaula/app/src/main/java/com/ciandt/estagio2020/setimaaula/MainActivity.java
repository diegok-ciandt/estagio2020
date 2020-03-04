package com.ciandt.estagio2020.setimaaula;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ciandt.estagio2020.setimaaula.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        setupObservers();
    }

    private void setupObservers() {
        viewModel.getResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String response) {
                if (!response.isEmpty()) {
                    showDialog();
                    viewModel.getResponse().setValue("");
                    ((TextView) findViewById(R.id.textView1)).setText("Dummy text");
                }
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Invalid password or email!");
        dialogBuilder.setTitle("Login error");
        dialogBuilder.setIcon(R.drawable.ic_alert);
        dialogBuilder.setNegativeButton("Cancel", null);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToNextScreen();
            }
        });
        dialogBuilder.show();
    }

    private void goToNextScreen() {
        Intent intent = new Intent(this, MainDataBindingActivity.class);
        startActivity(intent);
    }

}
