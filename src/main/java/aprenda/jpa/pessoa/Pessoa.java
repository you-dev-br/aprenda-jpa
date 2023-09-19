package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String email;
    @OneToMany
    private Set<Item> items = new HashSet<>();

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Pessoa(String nome, String email, Item ...items) {
        this.nome = nome;
        this.email = email;
        this.items = new HashSet<>(List.of(items));
    }
}
