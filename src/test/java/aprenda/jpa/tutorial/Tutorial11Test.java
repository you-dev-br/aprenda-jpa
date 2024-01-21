package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial11Test {
    @Autowired
    private MantenedorDeRepositorios mantenedorDeRepositorios;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void antesDeCadaTeste() {
        /*
         * Queremos limpar os repositorios garantir que os dados de testes anteriores
         * nao inteferam nos tests desta classe.
         */
        mantenedorDeRepositorios.apagarTudo();
    }

    @Test
    void paginacao() {
        // Poderia ser um for loop, mas estou usando o IntStream pelo 'cool factor'
        IntStream.rangeClosed(0, 4).forEach(i -> {
            var item = new Item();
            item.setNome("item_" + i);
            itemRepository.save(item);
        });
        assertEquals(5, itemRepository.count());
        var pagina_0 = itemRepository.findAll(PageRequest.ofSize(3));
        assertEquals(3, pagina_0.getNumberOfElements());
        assertEquals("item_0", pagina_0.getContent().get(0).getNome());
        assertEquals("item_1", pagina_0.getContent().get(1).getNome());
        assertEquals("item_2", pagina_0.getContent().get(2).getNome());

        var pagina_1 = itemRepository.findAll(pagina_0.nextPageable());
        assertEquals(2, pagina_1.getNumberOfElements());
        assertEquals("item_3", pagina_1.getContent().get(0).getNome());
        assertEquals("item_4", pagina_1.getContent().get(1).getNome());
    }
}
