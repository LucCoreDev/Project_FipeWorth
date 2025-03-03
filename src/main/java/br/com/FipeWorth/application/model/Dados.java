package br.com.FipeWorth.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record Dados(String codigo, String nome) {
}
