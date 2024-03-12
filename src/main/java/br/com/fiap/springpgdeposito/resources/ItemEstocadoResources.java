package br.com.fiap.springpgdeposito.resources;
import br.com.fiap.springpgdeposito.entity.ItemEstocado;
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
@RequestMapping(value = "/item")
public class ItemEstocadoResources {
    @Autowired
    private ItemEstocadoRepository repo;
    @Autowired
    private ProdutoRepository repoproduto;
    @Autowired
    private DepositoRepository repoDeposito;
    @Autowired
    private EnderecoRepository repoEndereco;
    @GetMapping
    public ResponseEntity<List<ItemEstocado>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemEstocado> findById(@PathVariable Long id) {

        var itemEstocado = repo.findById(id);

        if (itemEstocado.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(itemEstocado.get());
    }
    @Transactional
    @PostMapping
    public ResponseEntity<ItemEstocado> save(@RequestBody ItemEstocado itemEstocado) {
        ItemEstocado save = repo.save(itemEstocado);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}