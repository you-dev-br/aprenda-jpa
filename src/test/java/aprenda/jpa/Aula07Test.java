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

@SpringBootTest
class Aula07Test {
    private static final String PESSOA_NOME = "Bela";
    private static final String PESSOA_EMAIL = "bela@test.com";
    private static final String ITEM_NOME = "Game Boy";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    void salvarUmaNovaPessoaComItemEmTrasacao_Entao_PegarItemDaPessoa() {
        val pessoa = new Pessoa();
        pessoa.setNome(PESSOA_NOME);
        pessoa.setEmail(PESSOA_EMAIL);

        val item = new Item();
        item.setNome(ITEM_NOME);
        pessoa.getItems().add(item);

        pessoaRepository.save(pessoa);

        val pessoadoRepositorio = pessoaRepository.findById(pessoa.getId()).orElse(null);
        val itemDaPessoaDoRepositorio = pessoadoRepositorio.getItems().stream().findFirst().orElse(null);
        assertEquals(ITEM_NOME, itemDaPessoaDoRepositorio.getNome());
    }
}