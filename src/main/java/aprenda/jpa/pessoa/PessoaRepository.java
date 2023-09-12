package aprenda.jpa.pessoa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String nome);
    List<Pessoa> findByItems_Nome(String nomeDoItem);
}
