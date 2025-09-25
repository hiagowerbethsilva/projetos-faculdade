package com.example.cafeteria;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class Pedidocontroller {

    private static final Map<Long, Pedido> mapaPedidos = new ConcurrentHashMap<>();
    private static final AtomicLong idPedido = new AtomicLong();

    static {
        Long id1 = idPedido.incrementAndGet();
        mapaPedidos.put(id1, new Pedido(id1, "1 expresso", new BigDecimal("9.90"), "P"));
        Long id2 = idPedido.incrementAndGet();
        mapaPedidos.put(id2, new Pedido(id2, "1 café gelado", new BigDecimal("12.90"), "R"));
    }

    @GetMapping
    public Collection<Pedido> buscarTodos() {
        return mapaPedidos.values();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@RequestBody Pedido pedidoRequest) {
        long id = idPedido.incrementAndGet();
        pedidoRequest.setId(id);
        mapaPedidos.put(id, pedidoRequest);
        return pedidoRequest;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!mapaPedidos.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        mapaPedidos.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedidoRequest) {
        if (!mapaPedidos.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRequest.setId(id); // garante que o ID permaneça o mesmo
        mapaPedidos.put(id, pedidoRequest);
        return ResponseEntity.ok(pedidoRequest);
    }
}

