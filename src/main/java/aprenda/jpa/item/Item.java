package aprenda.jpa.item;

import aprenda.jpa.categoria.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private QrCode qrCode;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Categoria> categorias = new HashSet<>();

    public Item(String nome) {
        this.nome = nome;
    }

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
