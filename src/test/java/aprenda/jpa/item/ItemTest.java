package aprenda.jpa.item;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {
    @Autowired
    private ItemRepository itemRepository;
    private static final String NOME = "Epson LX300";
    private static final String NOME_ATUALIZADO = "Epson LX300 - Atualizado";
    private static final String DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String QR_CODE = "https://github.com/rafacandev/aprenda-jpa";

    @Test
    void salvarUmNovoItem_Entao_VerificarItemNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);
        itemRepository.save(novoItem);

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);
        assertEquals(novoItem.getId(), itemNoRepositorio.getId());
        assertEquals(NOME, itemNoRepositorio.getNome());
        assertEquals(DESCRICAO, itemNoRepositorio.getDescricao());
    }

    @Test
    void atualizarUmItemExistente_Entao_VerificarQueItemFoiAtualizadoNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);
        itemRepository.save(novoItem);

        itemRepository.findById(novoItem.getId())
                .ifPresent(itemParaAtualizar -> {
                    itemParaAtualizar.setNome(NOME_ATUALIZADO);
                    itemRepository.save(itemParaAtualizar);
                });

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);
        assertEquals(NOME_ATUALIZADO, itemNoRepositorio.getNome());
        assertEquals(DESCRICAO, itemNoRepositorio.getDescricao());
    }

    @Test
    void salvarItemComQrCode_Entao_VerificarItemNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);
        val qrCode = new QrCode();
        qrCode.setCode(QR_CODE);
        novoItem.setQrCode(qrCode);
        itemRepository.save(novoItem);

        itemRepository.findById(novoItem.getId())
                .map(Item::getQrCode)
                .ifPresentOrElse(
                        i -> assertEquals(QR_CODE, i.getCode()),
                        () -> fail("QrCode not found"));
    }
}