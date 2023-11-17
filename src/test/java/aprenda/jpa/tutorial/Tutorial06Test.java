package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.item.QrCode;
import aprenda.jpa.item.QrCodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Tutorial06Test {
    @Autowired
    private QrCodeRepository qrCodeRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void cascadeTypePersist() {
        var qrCode = new QrCode();
        qrCode.setCodigo("QrCode PERSIST");

        var item = new Item();
        item.setNome("Item PERSIST");
        item.setQrCode(qrCode);
        itemRepository.save(item); // EntityManager.persist()

        var itemDoRepositorio = itemRepository.findById(item.getId()).orElseThrow();
        assertNotNull(itemDoRepositorio.getQrCode());
        assertEquals("QrCode PERSIST", itemDoRepositorio.getQrCode().getCodigo());
    }

    @Test
    void cascadeTypeMerge() {
        var qrCode = new QrCode();
        qrCode.setCodigo("QrCode MERGE 1");

        var item = new Item();
        item.setNome("Item MERGE 1");
        item.setQrCode(qrCode);
        itemRepository.save(item); // EntityManager.persist()

        item.setNome("Item MERGE 2");
        qrCode.setCodigo("QrCode MERGE 2");
        itemRepository.save(item); // EntityManager.merge()

        var itemDoRepositorio = itemRepository.findById(item.getId()).orElseThrow();
        assertEquals("Item MERGE 2", itemDoRepositorio.getNome());
        assertNotNull(itemDoRepositorio.getQrCode());
        assertEquals("QrCode MERGE 2", itemDoRepositorio.getQrCode().getCodigo());
    }

    @Test
    void cascadeTypeRemove() {
        var qrCode = new QrCode();
        qrCode.setCodigo("QrCode REMOVE");

        var item = new Item();
        item.setNome("Item REMOVE");
        item.setQrCode(qrCode);
        itemRepository.save(item); // EntityManager.persist()

        itemRepository.delete(item); // EntityManager.remove()

        assertTrue(itemRepository.findById(item.getId()).isEmpty());
        assertTrue(qrCodeRepository.findById(qrCode.getId()).isEmpty());
    }
}
