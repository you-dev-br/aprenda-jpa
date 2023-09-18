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
class Tutorial04Test {
    private static final String PESSOA_NOME = "Ana";
    private static final String PESSOA_EMAIL = "ana@test.com";
    private static final String ITEM_NOME = "Epson LX300";
    private static final String ITEM_DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarPessoaComItem() {
        val pessoa = new Pessoa();
        pessoa.setNome(PESSOA_NOME);
        pessoa.setEmail(PESSOA_EMAIL);

        val item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        itemRepository.save(item);

        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        val pessoaDoRepositorio = pessoaRepository.findById(pessoa.getId()).orElse(null);
        assertNotNull(pessoaDoRepositorio);
        assertEquals(pessoa.getId(), pessoaDoRepositorio.getId());
        assertEquals(PESSOA_NOME, pessoaDoRepositorio.getNome());
        assertEquals(PESSOA_EMAIL, pessoaDoRepositorio.getEmail());

        val itemDoRepositorio = itemRepository.findById(item.getId());
        assertTrue(itemDoRepositorio.isPresent());
    }
}