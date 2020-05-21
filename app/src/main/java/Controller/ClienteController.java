package Controller;

import android.security.keystore.StrongBoxUnavailableException;

import java.security.PublicKey;

import Model.Cliente;

public class ClienteController {

    public static boolean validarDadosDoCliente(Cliente cliente, String email, String senha) {

        boolean retorno = (cliente.getEmail().equals(email)) && (cliente.getSenha().equals(senha));



        return retorno;
    }

        public static Cliente getClienteFake(){

            Cliente fake = new Cliente();
            fake.setPrimeiroNome("Caio");
            fake.setSobrenome("Henrique");
            fake.setSenha("12345");
            fake.setEmail("caio@tramar.com.br");
            fake.setPessoaFisica(true);

            return fake;
        }
    }


