# FipeWorth

FipeWorth é um aplicativo Java que permite consultar valores de veículos com base na Tabela FIPE. A aplicação consome a API **Parallelum Fipe** para buscar informações sobre marcas, modelos e valores de veículos, utilizando tecnologias modernas como Spring Boot, consumo de APIs REST e manipulação de JSON com a biblioteca Jackson.

## Tecnologias e Conceitos Utilizados

### 🔹 Java e Programação Orientada a Objetos (POO)

- Uso de classes, interfaces e records para estruturar o projeto.
- Aplicação de encapsulamento e boas práticas na organização do código.

### 🔹 Consumo de APIs REST

- A API Parallelum FIPE é utilizada para buscar os dados dos veículos.
- A classe `ConsumoAPI` usa `HttpURLConnection` para realizar as requisições HTTP.

### 🔹 Manipulação de JSON com Jackson

- Conversão de JSON para objetos Java usando a biblioteca **Jackson**.
- Uso de `@JsonIgnoreProperties` e `@JsonAlias` para mapear os dados corretamente.

### 🔹 Estrutura e Organização do Projeto

- **Camada de Serviço**: Contém a interface `IConverteDados` e a classe `ConverteDados` para manipular os dados recebidos da API.
- **Camada de Modelo**: Contém os records `Dados`, `Modelos` e `Veiculo`, representando os dados extraídos da API.
- **Classe Principal**: `Principal` gerencia a interação com o usuário, exibe menus e coordena as chamadas para as APIs.

## Funcionalidades

- O usuário pode escolher entre **Carro**, **Moto** ou **Caminhão**.
- O sistema retorna todas as marcas disponíveis para a categoria escolhida.
- O usuário pode buscar modelos e filtrar pelo nome.
- O sistema exibe os anos disponíveis e os valores de avaliação FIPE.

📌 **Desenvolvido por:** Lucas Correia

📖 **Créditos:**

Este projeto foi desenvolvido com base nos conhecimentos adquiridos na trilha de Java da Alura, abordando conceitos fundamentais como Programação Orientada a Objetos, consumo de APIs REST, manipulação de JSON com a biblioteca Jackson, além de boas práticas de organização de código. A plataforma Alura foi essencial para a construção deste projeto, proporcionando um aprendizado estruturado e aplicável ao desenvolvimento real de software.

