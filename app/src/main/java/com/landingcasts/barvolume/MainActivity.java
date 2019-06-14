package com.landingcasts.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editPanjang;
    private EditText editLebar;
    private EditText editTinggi;
    private Button btnHitung;
    private TextView tvHasil;

    private static final String STATE_HASIL = "state_hasil";

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString(STATE_HASIL, tvHasil.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPanjang = findViewById(R.id.edit_panjang);
        editLebar = findViewById(R.id.edit_lebar);
        editTinggi = findViewById(R.id.edit_tinggi);
        btnHitung = findViewById(R.id.button_hitung);
        tvHasil = findViewById(R.id.tv_hasil);

        btnHitung.setOnClickListener(this);

        if (savedInstanceState != null) {
            String hasil = savedInstanceState.getString(STATE_HASIL);
            tvHasil.setText(hasil);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_hitung) {
            String inputPanjang = editPanjang.getText().toString().trim();
            String inputLebar = editLebar.getText().toString().trim();
            String inputTinggi = editTinggi.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputPanjang)){
                isEmptyFields = true;
                editPanjang.setError("Field panjang tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLebar)){
                isEmptyFields = true;
                editLebar.setError("Field lebar tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputTinggi)){
                isEmptyFields = true;
                editTinggi.setError("Field tinggi tidak boleh kosong");
            }

            Double panjang = toDouble(inputPanjang);
            Double lebar = toDouble(inputLebar);
            Double tinggi = toDouble(inputTinggi);

            if (panjang == null){
                isInvalidDouble = true;
                editPanjang.setError("Field panjang harus berupa angka yang valid");
            }

            if (lebar == null) {
                isInvalidDouble = true;
                editLebar.setError("Field lebar harus berupa angka yang valid");
            }

            if (tinggi == null){
                isInvalidDouble = true;
                editTinggi.setError("Field tinggi harus berupa angka yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble){
                double volume = panjang * lebar * tinggi;
                tvHasil.setText(String.valueOf(volume));
            }
        }
    }

    Double toDouble(String str){
        try {
            return Double.valueOf(str);
        }catch (NumberFormatException e) {
            return null;
        }
    }
}
