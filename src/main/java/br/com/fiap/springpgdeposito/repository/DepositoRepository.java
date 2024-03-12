package br.com.fiap.springpgdeposito.repository;

import br.com.fiap.springpgdeposito.entity.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
}
