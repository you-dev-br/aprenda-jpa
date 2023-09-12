package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
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
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(PESSOA_1_NOME);
        novaPessoa.setEmail(PESSOA_1_EMAIL);

        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findByEmail(PESSOA_1_EMAIL).orElse(null);
        assertNotNull(pessoaNoRepositorio);
        assertEquals(novaPessoa.getId(), pessoaNoRepositorio.getId());
        assertEquals(PESSOA_1_NOME, pessoaNoRepositorio.getNome());
        assertEquals(PESSOA_1_EMAIL, pessoaNoRepositorio.getEmail());
    }

    @Test
    @Transactional
    void salvarUmaNovaPessoa_Entao_BuscarPorNomeDoItem() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(PESSOA_2_NOME);
        val novoItem = new Item();
        novoItem.setNome(ITEM_NOME);
        novaPessoa.getItems().add(novoItem);
        pessoaRepository.save(novaPessoa);

        val pessoasNoRepositorio = pessoaRepository.findByItems_Nome(ITEM_NOME);
        assertEquals(1, pessoasNoRepositorio.size());
        assertTrue(pessoasNoRepositorio.stream().anyMatch(p -> p.getId().equals(novaPessoa.getId())));
    }
}