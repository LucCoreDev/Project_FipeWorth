package br.com.FipeWorth.application.principal;

import br.com.FipeWorth.application.model.Dados;
import br.com.FipeWorth.application.model.Modelos;
import br.com.FipeWorth.application.service.ConsumoAPI;
import br.com.FipeWorth.application.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final ConsumoAPI consumo = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();
    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    // Criando o Menu
    public void exbiriMenu(){
        var menu = """
                Bem vindo ao FipeWorth!
                Veja as opções de veículo abaixo:
                
                *******
                1 - Carro
                2 - Moto
                3 - Caminhao
                *******
                
                Digite o número da opção desejada para consultar: 
                """;
        System.out.println(menu);
        String opcao = sc.nextLine().trim();

        // Substituir por switch por ser mais prático
        String endereco = switch (opcao){
            case "1" -> URL_BASE + "carros/marcas";
            case "2" -> URL_BASE + "motos/marcas";
            case "3" -> URL_BASE + "caminhoes/marcas";
            default -> null;
        };

        if (endereco == null){
            System.out.println("Opção inválida. Tente novamente");
            return;
        }

        // Verificando se o código não da null, para não dar problema na API
        try{
            String json = consumo.obterDados(endereco);
            // Sistema de segurança para saber se a API está sendo chamada certa
            if (json == null || json.isEmpty()){
                System.out.println("Erro em obter os dados da API!");
                return;
            }
            // criado através de uma classe genêrica e um record
            var marcas = conversor.obterLista(json, Dados.class);
            marcas.stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);
            System.out.println("Informe o condigo para consulta: ");
            var codigoMarca = sc.nextInt();
            // Referente ao modelo do carro
            endereco = endereco + "/" + codigoMarca + "/modelos";
            json = consumo.obterDados(endereco);
            var modeloLista = conversor.obterDados(json, Modelos.class);

            System.out.println("Modelos dessa marca são: ");
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);
        }catch (Exception e){
            System.out.println("Erro em busca os dados" + e.getMessage());
        }
    }
}
