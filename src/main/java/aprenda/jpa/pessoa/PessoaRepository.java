package aprenda.jpa.pessoa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String nome);
    Optional<Pessoa> findByNomeAndItems_Nome(String nome, String itemNome);
}
