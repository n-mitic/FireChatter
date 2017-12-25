package nsi.firechatter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private ImageButton fbBtn;
    private ImageButton twitterBtn;
    private ImageButton gplusBtn;
    private EditText emailEt;
    private EditText passwordEt;
    private Button loginBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fbBtn = findViewById(R.id.login_activity_fb_btn);
        twitterBtn = findViewById(R.id.login_activity_twitter_btn);
        gplusBtn = findViewById(R.id.login_activity_gplus_btn);
        emailEt = findViewById(R.id.login_activity_email_et);
        passwordEt = findViewById(R.id.login_activity_password_et);
        loginBtn = findViewById(R.id.login_activity_login_btn);

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFbLoginClick();
            }
        });
        twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTwitterLoginClick();
            }
        });
        gplusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGplusLoginClick();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });


        mAuth = FirebaseAuth.getInstance();
    }

    private void onFbLoginClick() {

    }

    private void onTwitterLoginClick() {

    }

    private void onGplusLoginClick() {
        
    }

    private void onLoginClick() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.login_activity_progress));
        progressDialog.setCancelable(false);
        progressDialog.show();

        final String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException ex) {
                                emailEt.setError(getString(R.string.login_activity_no_user_error));
                                emailEt.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException ex) {
                                if (ex.getErrorCode().equals("ERROR_INVALID_EMAIL")) {
                                    emailEt.setError(getString(R.string.login_activity_invalid_email_error));
                                    emailEt.requestFocus();
                                } else if (ex.getErrorCode().equals("ERROR_WRONG_PASSWORD")) {
                                    passwordEt.setError(getString(R.string.login_activity_invalid_password_error));
                                    passwordEt.requestFocus();
                                }
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

}