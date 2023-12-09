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
public class Tutorial08Test {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void buscaPorEmail() {
        var pessoa = new Pessoa();
        pessoa.setNome("Carlos");
        pessoa.setEmail("carlos@test.com");
        pessoaRepository.save(pessoa);

        var pessoaDoRepositorio = pessoaRepository.buscarPorEmail("carlos@test.com").orElseThrow();
        assertEquals("carlos@test.com", pessoaDoRepositorio.getEmail());
    }

    @Test
    void buscaPeloNomeDoItem() {
        var item = new Item();
        item.setNome("9999 Jogos em Um");
        itemRepository.save(item);

        var pessoa = new Pessoa();
        pessoa.setNome("Christiano");
        pessoa.setEmail("christiano@test.com");
        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        var pessoasDoRepositorio = pessoaRepository.buscarPeloNomeDoItem("9999 Jogos em Um");
        assertEquals(1, pessoasDoRepositorio.size());
        assertEquals(pessoa.getId(), pessoasDoRepositorio.get(0).getId());
    }
}
