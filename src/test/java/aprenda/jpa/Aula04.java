package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * - Aprenda JPA 04 - Relacionamento UmParaMuitos (@OneToMany)
 */
@SpringBootTest
class Aula04 {
    private static final String NOME = "Ana";
    private static final String EMAIL = "ana@test.com";
    private static final String ITEM_NOME = "Epson LX300";
    private static final String ITEM_DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String QR_CODE = "https://github.com/rafacandev/aprenda-jpa";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarUmaNovaPessoaComItem_Entao_VerificarPessoaNoRepositorio() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(NOME);
        novaPessoa.setEmail(EMAIL);

        val item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        novaPessoa.getItems().add(item);

        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findById(novaPessoa.getId()).orElse(null);
        assertNotNull(pessoaNoRepositorio);
        assertEquals(novaPessoa.getId(), pessoaNoRepositorio.getId());
        assertEquals(NOME, pessoaNoRepositorio.getNome());
        assertEquals(EMAIL, pessoaNoRepositorio.getEmail());

        val itemNoRepositorio = itemRepository.findById(item.getId());
        assertTrue(itemNoRepositorio.isPresent());
    }
}