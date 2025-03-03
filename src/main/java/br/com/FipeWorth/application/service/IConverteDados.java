package br.com.FipeWorth.application.service;
import java.util.List;

public interface IConverteDados {
    // Um tipo genêrico que converte dados para um classe
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
