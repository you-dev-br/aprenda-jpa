package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.item.QrCode;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Aprenda JPA 03 - Relacionamento UmParaUm (@OneToOne)
 */
@SpringBootTest
public class Tutorial03Test {
    private static final String ITEM_NOME = "Nokia 3210";
    private static final String QR_CODE = "https://github.com/rafacandev/aprenda-jpa";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarItemComQrCode() {
        val item = new Item();
        item.setNome(ITEM_NOME);
        val qrCode = new QrCode();
        qrCode.setCode(QR_CODE);
        item.setQrCode(qrCode);
        itemRepository.save(item);

        val itemDoRepositorio = itemRepository.findById(item.getId()).orElse(null);
        assertNotNull(itemDoRepositorio);
        assertNotNull(itemDoRepositorio.getQrCode());
        assertEquals(QR_CODE, itemDoRepositorio.getQrCode().getCode());
    }
}
