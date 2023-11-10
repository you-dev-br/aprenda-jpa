package aprenda.jpa.tutorial;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial05Test {
    private static final String CATEGORIA = "Equipamento eletrônico";
    private static final String ITEM_NOME = "Tocador the fitas VHS";
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    void salvarItemComCategoria() {
        var categoria = new Categoria(CATEGORIA);
        categoriaRepository.save(categoria);

        var item = new Item();
        item.setNome(ITEM_NOME);
        item.getCategorias().add(categoria);
        itemRepository.save(item);

        itemRepository.findById(item.getId()).ifPresentOrElse(itemDoRepositorio -> {
            assertEquals(1, itemDoRepositorio.getCategorias().size());
            var categoriaDoRepositorio = itemDoRepositorio.getCategorias().stream().findFirst().orElseThrow();
            assertEquals(new Categoria(CATEGORIA), categoriaDoRepositorio);
        }, Assertions::fail);
    }
}
