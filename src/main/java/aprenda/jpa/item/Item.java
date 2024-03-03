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
    @Column(nullable = false)
    private String nome;
    private String descricao;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private QrCode qrCode;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Categoria> categorias = new HashSet<>();
}
