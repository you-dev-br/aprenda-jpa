package aprenda.jpa.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String nome);
    List<Pessoa> findByItems_Nome(String nomeDoItem);
    Page<Pessoa> findByNomeContaining(String parteDoNome, Pageable pageable);
    @Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %:parteDoNome")
    List<Pessoa> buscarPorNomeContendo(String parteDoNome);
}
