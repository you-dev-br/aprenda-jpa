package aprenda.jpa.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String email);
    List<Pessoa> findByItems_nome(String nome);

    @Query("SELECT p FROM Pessoa p WHERE p.email = :email")
    Optional<Pessoa> buscarPorEmail(String email);

    @Query("SELECT p FROM Pessoa p JOIN p.items i WHERE i.nome = :nomeDoItem")
    List<Pessoa> buscarPeloNomeDoItem(String nomeDoItem);
}
