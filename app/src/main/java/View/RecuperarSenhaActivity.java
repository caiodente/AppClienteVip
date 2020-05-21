package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.daazi.v1.appclientevip.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    EditText editEmailCadastrado;
    Button btnRecuperar;
    Button btnVoltar;

    private SharedPreferences preferences;
    boolean isFormularioOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        initFormulario();

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()){

                    Toast.makeText(getApplicationContext(),
                            "Sua senha foi enviada para o email informado...",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecuperarSenhaActivity.this, LoginActivity.class);
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecuperarSenhaActivity.this, LoginActivity.class);
            }
        });
    }

    private void initFormulario() {

        editEmailCadastrado = findViewById(R.id.editEmailCadastrado);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editEmailCadastrado.getText().toString())) {
            editEmailCadastrado.setError("*");
            editEmailCadastrado.requestFocus();
            retorno = false;
        }

        return retorno;
    }
}
