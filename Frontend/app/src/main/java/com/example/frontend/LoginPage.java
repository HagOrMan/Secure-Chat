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

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        ImageButton backToMain = findViewById(R.id.returnMainLI);
        Button logInLI = findViewById(R.id.LIButton);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(LoginPage.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

        logInLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText usernameEditText = findViewById(R.id.usernameFieldLI);
                EditText passwordEditText = findViewById(R.id.passwordFieldLI);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // REPLACE WITH ACCOUNT AUTH THROUGH SERVER
                Boolean authenticated = true;

                if (authenticated) {
                    Intent logIn = new Intent(LoginPage.this, ChatList.class);
                    logIn.putExtra("username", username);
                    startActivity(logIn);
                } else {
                    Intent logInFailed = new Intent(LoginPage.this, MainActivity.class);
                    startActivity(logInFailed);
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