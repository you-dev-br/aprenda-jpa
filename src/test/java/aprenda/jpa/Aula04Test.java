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
 * Aprenda JPA 04 - Relacionamento UmParaMuitos (@OneToMany)
 */
@SpringBootTest
class Aula04Test {
    private static final String PESSOA_NOME = "Ana";
    private static final String PESSOA_EMAIL = "ana@test.com";
    private static final String ITEM_NOME = "Epson LX300";
    private static final String ITEM_DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarUmaNovaPessoaComItem_Entao_VerificarQuePessoaFoiSalva() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(PESSOA_NOME);
        novaPessoa.setEmail(PESSOA_EMAIL);

        val item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        novaPessoa.getItems().add(item);

        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findById(novaPessoa.getId()).orElse(null);
        assertNotNull(pessoaNoRepositorio);
        assertEquals(novaPessoa.getId(), pessoaNoRepositorio.getId());
        assertEquals(PESSOA_NOME, pessoaNoRepositorio.getNome());
        assertEquals(PESSOA_EMAIL, pessoaNoRepositorio.getEmail());

        val itemNoRepositorio = itemRepository.findById(item.getId());
        assertTrue(itemNoRepositorio.isPresent());
    }
}