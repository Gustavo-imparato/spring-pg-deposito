package br.com.fiap.springpgdeposito.resources;
import br.com.fiap.springpgdeposito.entity.Endereco;
import br.com.fiap.springpgdeposito.entity.ItemEstocado;
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
@RequestMapping(value = "/endereco")
public class EnderecoResources {
    @Autowired
    private EnderecoRepository repo;
    @Autowired
    private ProdutoRepository repoproduto;
    @Autowired
    private DepositoRepository repoDeposito;
    @Autowired
    private ItemEstocadoRepository repoItem;
    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id) {

        var endereco = repo.findById(id);

        if (endereco.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(endereco.get());
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
        Endereco save = repo.save(endereco);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}