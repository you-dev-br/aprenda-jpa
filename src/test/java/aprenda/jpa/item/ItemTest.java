package aprenda.jpa.item;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.categoria.CategoriaRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {
    private static final String NOME = "Epson LX300";
    private static final String NOME_ATUALIZADO = "Epson LX300 - Atualizado";
    private static final String DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String QR_CODE = "https://github.com/rafacandev/aprenda-jpa";
    private static final String CATEGORIA_NOME = "Impressora";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

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

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);
        assertNotNull(itemNoRepositorio.getQrCode());
        assertEquals(QR_CODE, itemNoRepositorio.getQrCode().getCode());
    }

    @Test
    void salvarItemComCategorias_Entao_VerificarNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);

        val novaCategoria = new Categoria(CATEGORIA_NOME);
        novoItem.getCategorias().add(novaCategoria);

        itemRepository.save(novoItem);


        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);

        val categoriaNoRepositorio = categoriaRepository.findById(novaCategoria.getNome()).orElse(null);
        assertNotNull(categoriaNoRepositorio);
        assertEquals(CATEGORIA_NOME, categoriaNoRepositorio.getNome());
    }
}