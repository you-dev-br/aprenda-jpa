package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial07Test {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void buscaPorEmail() {
        var pessoa = new Pessoa();
        pessoa.setNome("Bela");
        pessoa.setEmail("bela@test.com");
        pessoaRepository.save(pessoa);

        var pessoaDoRepositorio = pessoaRepository.findByEmail("bela@test.com").orElseThrow();
        assertEquals("Bela", pessoaDoRepositorio.getNome());
        assertEquals("bela@test.com", pessoaDoRepositorio.getEmail());
    }

    @Test
    void buscaPorNomeDoItem() {
        var item = new Item();
        item.setNome("Zip Drive");
        itemRepository.save(item);
        var pessoa = new Pessoa();
        pessoa.setNome("Beatriz");
        pessoa.setEmail("beatriz@test.com");
        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        var pessoasDoRepositorio = pessoaRepository.findByItems_nome("Zip Drive");
        assertEquals(1, pessoasDoRepositorio.size());
        assertEquals(pessoa.getId(), pessoasDoRepositorio.get(0).getId());
    }
}
