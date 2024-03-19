package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String email;
    @OneToMany
    @JoinTable(
            name = "emprestimo",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items = new HashSet<>();
}
