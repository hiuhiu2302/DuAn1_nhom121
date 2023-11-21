package com.example.duan1_nhom12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom12.database.DatabaseHelper;

public class DangNhap extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        databaseHelper = new DatabaseHelper(this);
        EditText usernameEditText=findViewById(R.id.edtuse_dn);
        EditText passwordEditText=findViewById(R.id.edtmk_dn);


        Button loginButton = findViewById(R.id.btn_dn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if( username.isEmpty()||password.isEmpty() ){
                    Toast.makeText(DangNhap.this, "Không được để trống", Toast.LENGTH_SHORT).show();

                }
                else {
                    String role = databaseHelper.checkCredentials(username, password);
                    if (role.equals("admin")) {
                        Intent intent = new Intent(DangNhap.this, Activity_admin.class);
                        startActivity(intent);
                    } else if (role.equals("khachhang")) {
                        Intent intent = new Intent(DangNhap.this, Activity_khachhang.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}