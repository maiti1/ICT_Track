package com.example.admin.timetracker;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCreate;
    private EditText edtEmailAddress;
    private EditText edtPassword;
    private TextView txtSignUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            firebaseAuth = FirebaseAuth.getInstance();
            btnCreate = (Button)findViewById(R.id.btnCreate);
            progressDialog = new ProgressDialog(this);

            btnCreate = (Button) findViewById(R.id.btnCreate);
            edtEmailAddress = (EditText) findViewById(R.id.email);
            edtPassword = (EditText) findViewById(R.id.password);
            txtSignUp = (TextView) findViewById(R.id.txtSignIn);

            btnCreate.setOnClickListener(this);
            txtSignUp.setOnClickListener(this);





        }

    private void registerUser() {
        String email = edtEmailAddress.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {

            //email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter your passwrod", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if validation are ok
        // we will show the progressDialog

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                        } else
                        {
                            Toast.makeText(MainActivity.this, "Could not Register Please Try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == btnCreate) {
            registerUser();
        }

        if (view == txtSignUp) {
            //will open login activity here
        }
    }
}
