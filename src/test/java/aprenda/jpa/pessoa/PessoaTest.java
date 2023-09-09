package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoaTest {
    private static final String NOME = "Ana";
    private static final String EMAIL = "ana@test.com";
    private static final String ITEM_NOME = "Epson LX300";
    private static final String ITEM_DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String QR_CODE = "https://github.com/rafacandev/aprenda-jpa";



    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarUmaNovaPessoa_Entao_VerificarPessoaNoRepositorio() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(NOME);
        novaPessoa.setEmail(EMAIL);
        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findById(novaPessoa.getId()).orElse(null);
        assertNotNull(pessoaNoRepositorio);
        assertEquals(novaPessoa.getId(), pessoaNoRepositorio.getId());
        assertEquals(NOME, pessoaNoRepositorio.getNome());
        assertEquals(EMAIL, pessoaNoRepositorio.getEmail());
    }


    @Test
    void salvarUmaNovaPessoaComItem_Entao_VerificarPessoaNoRepositorio() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(NOME);
        novaPessoa.setEmail(EMAIL);
        val item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        novaPessoa.getItems().add(item);

        pessoaRepository.save(novaPessoa);

        val pessoaNoRepositorio = pessoaRepository.findById(novaPessoa.getId()).orElse(null);
        assertNotNull(pessoaNoRepositorio);
        assertEquals(novaPessoa.getId(), pessoaNoRepositorio.getId());
        assertEquals(NOME, pessoaNoRepositorio.getNome());
        assertEquals(EMAIL, pessoaNoRepositorio.getEmail());

        val itemNoRepository = itemRepository.findById(item.getId());
        assertTrue(itemNoRepository.isPresent());
    }
}