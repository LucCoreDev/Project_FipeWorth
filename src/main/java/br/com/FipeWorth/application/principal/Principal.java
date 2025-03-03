package br.com.FipeWorth.application.principal;

import br.com.FipeWorth.application.service.ConsumoAPI;

import java.net.URL;
import java.util.Scanner;

public class Principal {
    Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    // Criando o Menu
    public void exbiriMenu(){
        var menu = """
                Bem vindo ao FipeWorth
                Veja as opções de veículo abaixo:
                
                *******
                Carro
                Moto
                Caminhao
                *******
                
                Digite uma das opções para consultar: 
                """;
        System.out.println(menu);
        var opcao = sc.nextLine();
        String endereco = null;

        // Verificando as opções e atribuindo à API
        if(opcao.toLowerCase().contains("carro")){
            endereco = URL_BASE + "carros/marcas";
        } else if(opcao.toLowerCase().contains("moto")){
            endereco = URL_BASE + "motos/marcas";
        } else if (opcao.toLowerCase().contains("caminhao")){
            endereco = URL_BASE + "caminhoes/marcas";
        }

        // Verificando se o código não da null, para não dar problema na API
        if(endereco != null){
            var json = consumo.obterdados(endereco);
            System.out.println(json);
        } else {
            System.out.println("Opção inválida");
        }
    }
}
