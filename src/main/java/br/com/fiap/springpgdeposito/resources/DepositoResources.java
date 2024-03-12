package br.com.fiap.springpgdeposito.resources;
import br.com.fiap.springpgdeposito.entity.Deposito;
import br.com.fiap.springpgdeposito.entity.Endereco;
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
@RequestMapping(value = "/deposito")
public class DepositoResources {

    @Autowired
    private DepositoRepository repo;
    @Autowired
    private ProdutoRepository repoproduto;
    @Autowired
    private EnderecoRepository repoEndereco;
    @Autowired
    private ItemEstocadoRepository repoItem;
    @GetMapping
    public ResponseEntity<List<Deposito>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Deposito> findById(@PathVariable Long id) {

        var deposito = repo.findById(id);

        if (deposito.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deposito.get());
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Deposito> save(@RequestBody Deposito deposito) {
        Deposito save = repo.save(deposito);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}