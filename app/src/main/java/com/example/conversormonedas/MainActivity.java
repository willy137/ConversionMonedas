package com.example.conversormonedas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.conversormonedas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText ed1;
    private EditText ed2;

    private char selec;

    private Button b1;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        ed1=binding.etEuros;
        ed2=binding.etDolares;
        ed1.setEnabled(false);
        ed2.setEnabled(false);
        b1=binding.btConvertir;
        b1.setEnabled(false);


        mv.getResultado().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvCambio.setText("Cambio a "+s);
            }
        });
        mv.getCambio().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double resul) {
                binding.etCambio.setText(resul+"");
            }
        });

        binding.rbDolar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ed2.setEnabled(true);
                ed1.setEnabled(false);
                b1.setEnabled(true);
                selec='D';
            }
        });

        binding.rbEuro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ed1.setEnabled(true);
                ed2.setEnabled(false);
                b1.setEnabled(true);
                selec='E';
            }
        });

        binding.btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mv.Cambio(binding.etEuros.getText().toString(),binding.etDolares.getText().toString(),selec);
            }
        });
    }
}