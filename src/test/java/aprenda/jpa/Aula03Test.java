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
public class Aula03Test {
    private static final String ITEM_NOME = "Nokia 3210";
    private static final String QR_CODE = "https://github.com/rafacandev/aprenda-jpa";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarItemComQrCode_Entao_VerificarQueItemFoiSalvo() {
        val novoItem = new Item();
        novoItem.setNome(ITEM_NOME);
        val qrCode = new QrCode();
        qrCode.setCode(QR_CODE);
        novoItem.setQrCode(qrCode);
        itemRepository.save(novoItem);

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);
        assertNotNull(itemNoRepositorio.getQrCode());
        assertEquals(QR_CODE, itemNoRepositorio.getQrCode().getCode());
    }
}
