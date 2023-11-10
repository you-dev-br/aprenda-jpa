package aprenda.jpa.item;

import aprenda.jpa.categoria.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String descricao;
    @OneToOne
    private QrCode qrCode;
    @ManyToMany
    private Set<Categoria> categorias = new HashSet<>();
}
