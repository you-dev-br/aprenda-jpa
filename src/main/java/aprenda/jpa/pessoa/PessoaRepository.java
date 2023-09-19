package aprenda.jpa.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String nome);
    List<Pessoa> findByItems_Nome(String nomeDoItem);
}
