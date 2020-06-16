package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.List;

import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import Util.AppUtil;
import app.daazi.v1.appclientevip.R;
/**
 * Tela principal, temos a hora e data atual no momento do App usando o Get no App util que controla dias e horas.
 * Temos a intent para tela de Cadastro de usuário.
 **/
public class MainActivity extends AppCompatActivity {

    Cliente cliente;
    ClientePJ clientePJ;
    ClientePF clientePF;
    TextView txtNomeCliente;

    private SharedPreferences preferences;
    List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();
        
      //  buscarListaClientes();


    }

    private void buscarListaClientes() {
        clientes = new ArrayList<>();
        clientes.add(cliente);

        for (int i = 0; i <50; i++) {
            cliente = new Cliente();
            cliente.setPrimeiroNome("Cliente nº "+i);
            clientes.add(cliente);
        }

        for (Cliente obj: clientes ) {

            Log.i(AppUtil.LOG_APP,"Obj: "+obj.getPrimeiroNome());

        }
    }


    private void initFormulario() {

        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();
        txtNomeCliente = findViewById(R.id.txtNomeCliente);

        restaurarSharedPreferences();

        txtNomeCliente.setText("Bem vindo, "+ cliente.getPrimeiroNome());

    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        // Objeto Cliente
        cliente.setPrimeiroNome(preferences.getString("PrimeiroNome", "nulo"));
        cliente.setSobrenome(preferences.getString("Sobrenome", "nulo"));
        cliente.setEmail(preferences.getString("Email", "nulo"));
        cliente.setSenha(preferences.getString("Senha", "nulo"));
        cliente.setPessoaFisica(preferences.getBoolean("PessoaFisica", true));

        // Objeto cliente pessoa fisica

        clientePF.setCpf(preferences.getString("Cpf", "nulo"));
        clientePF.setNomeCompleto(preferences.getString("NomeCompleto", "nulo"));

        // Objeto cliente pessoa Juridica

        clientePJ.setCnpj(preferences.getString("Cnpj", "nulo"));
        clientePJ.setRazaoSocial(preferences.getString("RazaoSocial", "nulo"));
        clientePJ.setSimplesNacional(preferences.getBoolean("SimplesNscional", false));
        clientePJ.setMei(preferences.getBoolean("Mei", false));
        clientePJ.setDataAbertura(preferences.getString("DataAbertura", "nulo"));

    }

    public void meusDados(View view) {
        Log.i(AppUtil.LOG_APP, "-----DADOS CLIENTES-----");
        Log.i(AppUtil.LOG_APP, "Primeiro Nome: "+cliente.getPrimeiroNome());
        Log.i(AppUtil.LOG_APP, "Sobrenome: "+cliente.getSobrenome());
        Log.i(AppUtil.LOG_APP, "Email: "+cliente.getEmail());
        Log.i(AppUtil.LOG_APP, "Senha: "+cliente.getSenha());
        Log.i(AppUtil.LOG_APP, "ID: "+cliente.getId());
        Log.i(AppUtil.LOG_APP, "-----DADOS CLIENTES PF-----");
        Log.i(AppUtil.LOG_APP, "CPF: "+clientePF.getCpf());
        Log.i(AppUtil.LOG_APP, "Nome Completo: "+clientePF.getNomeCompleto());

        if (!cliente.isPessoaFisica()) {

            Log.i(AppUtil.LOG_APP, "-----DADOS CLIENTES PJ-----");
            Log.i(AppUtil.LOG_APP, "CNPJ: " + clientePJ.getCnpj());
            Log.i(AppUtil.LOG_APP, "Razão Social: " + clientePJ.getRazaoSocial());
            Log.i(AppUtil.LOG_APP, "Data Abertura: " + clientePJ.getDataAbertura());
            Log.i(AppUtil.LOG_APP, "Simples Nacional: " + clientePJ.isSimplesNacional());
            Log.i(AppUtil.LOG_APP, "MEI: " + clientePJ.isMei());

        }

    }

    public void atualizarMeusDados(View view) {

        if (cliente.isPessoaFisica()) {

            cliente.setPrimeiroNome("Marco");
            cliente.setSobrenome("Oliveira");

            clientePF.setNomeCompleto("Marco A Oliveira");

            // salvarSharedPreferences();
            Log.i(AppUtil.LOG_APP, "-----ALTERANDO DADOS CLIENTE-----");
            Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
            Log.i(AppUtil.LOG_APP, "Sobrenome: " + cliente.getSobrenome());

            Log.i(AppUtil.LOG_APP, "-----ALTERANDO DADOS CLIENTES PF-----");
            Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());
        } else {
            clientePJ.setRazaoSocial("MADDO ME");
            Log.i(AppUtil.LOG_APP, "-----ALTERANDO DADOS CLIENTES PJ-----");
            Log.i(AppUtil.LOG_APP, "Razão Social: " + clientePJ.getRazaoSocial());

        }
    }

    public void excluirMinhaConta(View view) {

        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("EXCLUIR SUA CONTA ?")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Confirma exclusão definitiva de sua conta?")
                .setNegativeBtnText("NÃO")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+"Sua conta foi excluida, esperamos que retorne em breve..." ,
                                Toast.LENGTH_SHORT).show();
                        cliente = new Cliente();
                        clientePF = new ClientePF();
                        clientePJ = new ClientePJ();
                        //lembrar senha tem q resetar.
                       // salvarSharedPreferences();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),"Ai sim, continue com a gente!",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();


    }

    public void consultarClientesVip(View view) {

        Intent intent = new Intent(MainActivity.this,ConsultarClientesActivity.class);
        startActivity(intent);
    }

    public void sairDoAplicativo(View view) {

        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Deseja realmente sair do aplicativo ?")
                .setNegativeBtnText("NÃO")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+", volte sempre!" ,
                                Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),"Ai sim, continue com a gente!",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

}
