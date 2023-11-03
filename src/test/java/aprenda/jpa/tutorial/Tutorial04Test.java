package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial04Test {
    private static final String ITEM_NOME = "Sony Walkman";
    private static final String PESSOA_NOME = "Ana";
    private static final String PESSOA_EMAIL = "ana@email.com";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    @Transactional
    void salvarPessoaComItem() {
        var item = new Item();
        item.setNome(ITEM_NOME);
        itemRepository.save(item);

        var pessoa = new Pessoa();
        pessoa.setNome(PESSOA_NOME);
        pessoa.setEmail(PESSOA_EMAIL);
        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        pessoaRepository.findById(pessoa.getId())
                .ifPresentOrElse(pessoaDoRepositorio -> {
                    assertEquals(PESSOA_NOME, pessoaDoRepositorio.getNome());
                    assertEquals(PESSOA_EMAIL, pessoaDoRepositorio.getEmail());
                    assertEquals(1, pessoaDoRepositorio.getItems().size());

                    var itemDoRepositorio = pessoaDoRepositorio.getItems().stream().findFirst().orElseThrow();
                    assertEquals(ITEM_NOME, itemDoRepositorio.getNome());
                }, Assertions::fail);
    }
}
