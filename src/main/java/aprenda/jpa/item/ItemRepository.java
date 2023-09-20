package aprenda.jpa.item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query("SELECT i FROM Item i WHERE i.descricao LIKE %:palavraChave")
    List<Item> buscarDescricaoContendo(String palavraChave);
}
