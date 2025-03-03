package br.com.FipeWorth.application.service;

public interface IConverteDados {
    // Um tipo genêrico que converte dados para um classe
    <T> T obterDados(String json, Class<T> classe);
}
