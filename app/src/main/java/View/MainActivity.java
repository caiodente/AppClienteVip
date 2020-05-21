package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Util.AppUtil;
import app.daazi.v1.appclientevip.R;
/**
 * Tela principal, temos a hora e data atual no momento do App usando o Get no App util que controla dias e horas.
 * Temos a intent para tela de Cadastro de usuário.
 **/
public class MainActivity extends AppCompatActivity {
    //nome da classe, na sequencia declarar o objeto.
    TextView txtTitulo;
    TextView txtDataAtual;
    TextView txtHoraAtual;
    Button btnCadastrarNovoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtTitulo = findViewById(R.id.txtTitulo);
        txtTitulo.setText("Aplicativo do Caio");
        txtTitulo.setTextColor(getResources().getColor(R.color.colorTextView));

            txtDataAtual = findViewById(R.id.textDataAtual);
            txtDataAtual.setText(AppUtil.getDataAtual());

                txtHoraAtual = findViewById(R.id.texthoraAtual);
                txtHoraAtual.setText(AppUtil.getHoraAtual());

        btnCadastrarNovoUsuario = findViewById(R.id.btnCadastrarNovoUsuario);
        btnCadastrarNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iTelaCadastro = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
                startActivity(iTelaCadastro);
            }
        });


    }
}
