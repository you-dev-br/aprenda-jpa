package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Pessoa {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();
}
