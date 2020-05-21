package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import Util.AppUtil;
import app.daazi.v1.appclientevip.R;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    boolean isLembrarSenha = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
/**
 * Declaramos o método iniciar aplicativo onde quando executado faz a intent com Login Activity.
 * Essa tela utiliza o TIME SPLASH da App util, pois lá temos a hora e assim conseguimos contar os segundos.
 **/

        salvarSharedPreferences();
       restaurarSharedPreferences();

        iniciarAplicativo(); // método

    }


    private void iniciarAplicativo() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;


                if (isLembrarSenha) {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
                return;
            }

        }, AppUtil.TIME_SPLASH);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }


}
