package com.example.cafeteria;

import java.math.BigDecimal;

public class Pedido {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private String status;

    public Pedido() {} // construtor vazio para o Spring

    public Pedido(Long id, String descricao, BigDecimal valor, String status) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
