package br.com.fiap.springpgdeposito.resources;
import br.com.fiap.springpgdeposito.entity.Produto;
import br.com.fiap.springpgdeposito.repository.DepositoRepository;
import br.com.fiap.springpgdeposito.repository.EnderecoRepository;
import br.com.fiap.springpgdeposito.repository.ItemEstocadoRepository;
import br.com.fiap.springpgdeposito.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResources {
    @Autowired
    private ProdutoRepository repo;
    @Autowired
    private ItemEstocadoRepository repoItem;
    @Autowired
    private DepositoRepository repoDeposito;
    @Autowired
    private EnderecoRepository repoEndereco;
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {

        var produto = repo.findById(id);

        if (produto.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(produto.get());
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        Produto save = repo.save(produto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}