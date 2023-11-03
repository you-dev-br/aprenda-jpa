package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Set<Item> items = new HashSet<>();
}
