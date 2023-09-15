package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Aprenda JPA 08 - Busca avancada: Pelo Nome do Metodo
 */
@SpringBootTest
class Aula08Test {
    private static final String PESSOA_1_NOME = "Bela";
    private static final String PESSOA_1_EMAIL = "bela@test.com";
    private static final String PESSOA_2_NOME = "Beatriz";
    private static final String ITEM_NOME = "Zip Drive";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void salvarUmaNovaPessoa_Entao_BuscarPorEmail() {
        val pessoa = new Pessoa();
        pessoa.setNome(PESSOA_1_NOME);
        pessoa.setEmail(PESSOA_1_EMAIL);

        pessoaRepository.save(pessoa);

        val pessoaDoRepositorio = pessoaRepository.findByEmail(PESSOA_1_EMAIL).orElse(null);
        assertNotNull(pessoaDoRepositorio);
        assertEquals(pessoa.getId(), pessoaDoRepositorio.getId());
        assertEquals(PESSOA_1_NOME, pessoaDoRepositorio.getNome());
        assertEquals(PESSOA_1_EMAIL, pessoaDoRepositorio.getEmail());
    }

    @Test
    @Transactional
    void salvarUmaNovaPessoa_Entao_BuscarPorNomeDoItem() {
        val pessoa = new Pessoa();
        pessoa.setNome(PESSOA_2_NOME);
        val item = new Item();
        item.setNome(ITEM_NOME);
        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        val pessoasDoRepositorio = pessoaRepository.findByItems_Nome(ITEM_NOME);
        assertEquals(1, pessoasDoRepositorio.size());
        assertTrue(pessoasDoRepositorio.stream().anyMatch(p -> p.getId().equals(pessoa.getId())));
    }
}