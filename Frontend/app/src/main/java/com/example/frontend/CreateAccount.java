package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        ImageButton backToMain = findViewById(R.id.returnMainCA);
        Button createAccountCA = findViewById(R.id.CAButton);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

        createAccountCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText usernameEditText = findViewById(R.id.usernameFieldCA);
                EditText passwordEditText = findViewById(R.id.passwordFieldCA);
                EditText repasswordEditText = findViewById(R.id.passwordREFieldCA);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String repassword = repasswordEditText.getText().toString();

                // SEND USERNAME AND PASSWORD TO SERVER AND AWAIT RESPONSE
                if (password == repassword) {
                    Intent createAccount = new Intent(CreateAccount.this, MainActivity.class);
                    startActivity(createAccount);
                }

                else {
                    Intent passfailed = new Intent(CreateAccount.this, MainActivity.class);
                    startActivity(passfailed);
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}