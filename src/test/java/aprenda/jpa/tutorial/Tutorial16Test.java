package aprenda.jpa.tutorial;

import aprenda.jpa.item.Detalhes;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Tutorial16Test {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void jsonNoBancoDeDados() {
        var item = new Item();
        item.setNome("Vai e vem");
        var detalhes = new Detalhes("Garrafa Pet");
        item.setDetalhes(detalhes);
        itemRepository.save(item);

        var itemDoRepositorio = itemRepository.findById(item.getId()).orElseThrow();
        System.out.println(itemDoRepositorio);
    }
}
