package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.item.QrCode;
import aprenda.jpa.item.QrCodeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class Tutorial03Test {
    private static final String ITEM_NOME = "Nokia 3210";
    private static final String QR_CODE_CODIGO = "https://github.com/rafacandev/aprenda-jpa";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Test
    void salvarItemComQrCode() {
        var item = new Item();
        item.setNome(ITEM_NOME);

        var qrCode = new QrCode();
        qrCode.setCodigo(QR_CODE_CODIGO);
        qrCodeRepository.save(qrCode);

	    item.setQrCode(qrCode);
        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresentOrElse(itemDoRepositorio -> {
                    assertNotNull(itemDoRepositorio.getQrCode());
                    assertEquals(QR_CODE_CODIGO, itemDoRepositorio.getQrCode().getCodigo());
                }, Assertions::fail);
    }
}
