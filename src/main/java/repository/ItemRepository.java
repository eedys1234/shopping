package repository;

import domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {

        if(Objects.isNull(item.getId())) {
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    public List<Item> findAll() {
        return em.createQuery("select i from item i", Item.class)
                .getResultList();
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
}
