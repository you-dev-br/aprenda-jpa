package aprenda.jpa.tutorial;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
public class Tutorial10Test {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void buscarPessoaPorEmail() {
        var pessoa = new Pessoa();
        pessoa.setNome("Elis");
        pessoa.setEmail("elis@test.com");
        pessoaRepository.save(pessoa);

        var referencia = new Pessoa();
        referencia.setEmail("elis@test.com");
        var exemplo = Example.of(referencia);

        var pessoaDoRepositorio = pessoaRepository.findOne(exemplo).orElseThrow();
        assertEquals("elis@test.com", pessoaDoRepositorio.getEmail());
    }

    @Test
    void buscaPorDominioDoEmail() {
        var pessoa = new Pessoa();
        pessoa.setNome("Everton");
        pessoa.setEmail("everton@test.com.br");
        pessoaRepository.save(pessoa);

        var referencia = new Pessoa();
        referencia.setEmail("@test.com.br");
        var matcher = ExampleMatcher
                .matching()
                .withMatcher("email", endsWith());
        var exemplo = Example.of(referencia, matcher);

        var pessoasDoRepositorio = pessoaRepository.findAll(exemplo);
        assertEquals(1, pessoasDoRepositorio.size());
        assertEquals("everton@test.com.br", pessoasDoRepositorio.get(0).getEmail());
    }
}
