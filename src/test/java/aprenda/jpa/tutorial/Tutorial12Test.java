package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial12Test {
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
    void ordernarPorAtributo() {
        rangeClosed(0, 2).forEach(i -> {
            var item = new Item();
            item.setNome("item-" + i);
            itemRepository.save(item);
        });

        var sort = Sort.by(Sort.Direction.DESC, "nome");
        var itensDoRepositorio = itemRepository.findAll(sort);
        assertEquals("item-2", itensDoRepositorio.get(0).getNome());
        assertEquals("item-1", itensDoRepositorio.get(1).getNome());
        assertEquals("item-0", itensDoRepositorio.get(2).getNome());
    }

    @Test
    void ordernarComPaginacao() {
        rangeClosed(0, 2).forEach(i -> {
            var item = new Item();
            item.setNome("item-" + i);
            itemRepository.save(item);
        });

        var sort = Sort.by(Sort.Direction.DESC, "nome", "id");
        var paginacao = PageRequest.of(0, 3, sort);
        var itensDoRepositorio = itemRepository.findAll(paginacao).getContent();
        assertEquals("item-2", itensDoRepositorio.get(0).getNome());
        assertEquals("item-1", itensDoRepositorio.get(1).getNome());
        assertEquals("item-0", itensDoRepositorio.get(2).getNome());
    }
}