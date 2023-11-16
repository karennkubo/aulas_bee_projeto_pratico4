package com.ambev.projetopratico4.rest;

import com.ambev.projetopratico4.model.Produto;
import com.ambev.projetopratico4.repository.ProdutoRepository;
import com.ambev.projetopratico4.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** Teste:
curl -X POST -H "Content-Type: application/json" -d '{
 "nome": "Produto de Exemplo",
"descricao": "desc de exemplo",
"preco": 29.99
}' http://localhost:8080/api/products
 *
 */


 /*curl -X POST -H "Content-Type: application/json" -d '{
         "nome": "Produto de Exemplo",
         "descricao": "desc de exemplo",
         "preco": 29.99
         }' http://localhost:8080/api/produtos*/

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/consultar/id/{id}")
    public ResponseEntity<Optional<Produto>> consultarProdutoPeloId(@PathVariable String id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar/nome/{nome}")
    public ResponseEntity<List<Produto>> consultarProdutoPeloNome(@PathVariable String nome) {
        List<Produto> produto = produtoService.consultarPorNome(nome);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<Produto>> consultarTodosOsProdutos() {
        List<Produto> produtos = produtoService.consultarProdutos();
        if (produtos != null) {
            return ResponseEntity.ok(produtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/error")
    public ResponseEntity errorProduct() {
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
