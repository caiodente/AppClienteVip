package Util;


import java.util.Calendar;

/**
 * Classe de apoio contendo métodos que podem ser reutilizados em todo o projeto.
 * criada por: Caio Henrique 01/2020.
 * Versão 1.
 */


public class AppUtil
{
    public static final int TIME_SPLASH = 5 * 1000;
    public static final String PREF_APP = "app_cliente_vip_pref";

    /**
     * static é um metodo que não necessita de um objeto
     * aqui retorna a data Atual usando um mpetodo nativo do Android Studio que é CALENDAR.
     */
    public static String getDataAtual () {
        String dataAtual = "00/00/0000";
        String dia, mes, ano;
            try {
                Calendar calendar = Calendar.getInstance();
                dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                mes = String.valueOf(calendar.get(Calendar.MONTH) +1);
                ano = String.valueOf(calendar.get(Calendar.YEAR));
                if (calendar.get(calendar.DAY_OF_MONTH)<10){ dia = "0"+dia;}
                if (calendar.get(calendar.MONTH) + 1 < 10) { mes = "0"+mes;}
                dataAtual = dia+"/"+mes+"/"+ano;
                }
            catch (Exception e){}
                return dataAtual;
    }

    /**
     *
     * Retorno da Hora atual, utilizando o método nativo do Android CALENDAR.
     */
    public static String getHoraAtual () {

        String horaAtual = "00:00:00";
        String hora, minuto, segundo;

        try {

            Calendar calendar = Calendar.getInstance();
                    int iHora = calendar.get(Calendar.HOUR_OF_DAY);
                    int iMinuto = calendar.get(Calendar.MINUTE);
                    int iSegundo = calendar.get(Calendar.MINUTE);

                    hora = (iHora <=9) ? "0"+iHora : Integer.toString(iHora);
                    minuto = (iMinuto <=9) ? "0"+iMinuto : Integer.toString(iMinuto);
                    segundo = (iSegundo <=9) ? "0"+iSegundo : Integer.toString(iSegundo);

                    horaAtual = hora+":"+minuto+":"+segundo;

                    return horaAtual;

        } catch (Exception e) {

        }


        return horaAtual;
    }

}
