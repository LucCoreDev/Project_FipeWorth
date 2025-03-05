package br.com.FipeWorth.application.principal;

import br.com.FipeWorth.application.model.Dados;
import br.com.FipeWorth.application.model.Modelos;
import br.com.FipeWorth.application.model.Veiculo;
import br.com.FipeWorth.application.service.ConsumoAPI;
import br.com.FipeWorth.application.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
            AtomicReference<String> json = new AtomicReference<>(consumo.obterDados(endereco));
            // Sistema de segurança para saber se a API está sendo chamada certa
            if (json.get() == null || json.get().isEmpty()){
                System.out.println("Erro em obter os dados da API!");
                return;
            }
            // criado através de uma classe genêrica e um record
            var marcas = conversor.obterLista(json.get(), Dados.class);
            marcas.stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);
            System.out.println("Informe o condigo para consulta: ");
            var codigoMarca = sc.nextInt();
            sc.nextLine();

            // Referente ao modelo do carro
            endereco = endereco + "/" + codigoMarca + "/modelos";
            json.set(consumo.obterDados(endereco));
            var modeloLista = conversor.obterDados(json.get(), Modelos.class);

            if (modeloLista == null || modeloLista.modelos() == null) {
                System.out.println("Nenhum modelo encontrado para essa marca.");
                return;
            }

            System.out.println("Modelos dessa marca são: ");
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);

            System.out.println("\n Digite um trecho do nome do carro a ser buscado: ");
            var nomeVeiculo = sc.nextLine();
            List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                    .collect(Collectors.toList());

            System.out.println("\n modelos filtrados");
            modelosFiltrados.forEach(System.out::println);

            System.out.println("Digite o código do modelo para busca os valores de avaliação: ");
            var codigoDoModelo = sc.nextLine();

            endereco = endereco + "/" + codigoDoModelo + "/anos";
            json.set(consumo.obterDados(endereco));
            List<Dados> anos = conversor.obterLista(json.get(), Dados.class);
            List<Veiculo> veiculos = new ArrayList<>();

            String finalEndereco = endereco;
            anos.forEach(ano -> {
                var enderecoAnos = finalEndereco + "/" + ano.codigo();
                String jsonAno = consumo.obterDados(enderecoAnos);

                if (jsonAno == null || jsonAno.isEmpty()) {
                    System.out.println("Erro ao obter dados para o ano: " + ano.codigo());
                    return;
                }

                Veiculo veiculo = conversor.obterDados(jsonAno, Veiculo.class);
                if (veiculo != null) {
                    veiculos.add(veiculo);
                }
            });

            System.out.println("\n Todos os veiculos filtrados com a avaliações por ano: ");
            veiculos.forEach(System.out::println);

        }catch (Exception e){
            System.out.println("Erro em busca os dados" + e.getMessage());
        }
    }
}
