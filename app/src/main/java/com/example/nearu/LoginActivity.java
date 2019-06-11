package com.example.nearu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTRN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$");
    private Button login_button, register_button;
    private TextInputLayout textInputEmail, textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_button = (Button) findViewById(R.id.register_button_l);
        login_button = (Button) findViewById(R.id.login_button_l);

        textInputEmail = findViewById(R.id.email_reg_l);
        textInputPassword = findViewById(R.id.password_reg_l);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
                finish();
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConfirmInput() == true)
                {
                    openMenuActivity();
                    finish();
                }
            }
        });
    }
    public void openRegisterActivity()
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty())
        {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordImput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordImput.isEmpty())
        {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else if(!PASSWORD_PATTRN.matcher(passwordImput).matches()){
            textInputPassword.setError("Password too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public boolean ConfirmInput()
    {
        boolean condition = true;
        if (validateEmail() == false)
        {
            condition = false;
        }
        if (validatePassword() == false)
        {
            condition = false;
        }
        return condition;
    }
    public void openMenuActivity()
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
