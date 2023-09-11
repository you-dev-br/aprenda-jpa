package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
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
    private static final String NOME = "Bela";
    private static final String EMAIL = "bela@test.com";
    private static final String ITEM_NOME = "Zip Drive";
    private static final String ITEM_DESCRICAO = "Iomega Zip Drive de 250Mb";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void salvarUmaNovaPessoa_Entao_BuscarPorNome() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(NOME);
        novaPessoa.setEmail(EMAIL);

        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findByEmail(EMAIL).orElse(null);
        assertNotNull(pessoaNoRepositorio);
        assertEquals(novaPessoa.getId(), pessoaNoRepositorio.getId());
        assertEquals(NOME, pessoaNoRepositorio.getNome());
    }

    @Test
    void salvarUmaNovaPessoa_Entao_BuscarPorNomeEEmail() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(NOME);

        val novoItem = new Item();
        novoItem.setNome(ITEM_NOME);
        novoItem.setDescricao(ITEM_DESCRICAO);
        novaPessoa.getItems().add(novoItem);

        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findByNomeAndItems_Nome(NOME, ITEM_NOME);
        assertTrue(pessoaNoRepositorio.isPresent());
    }
}