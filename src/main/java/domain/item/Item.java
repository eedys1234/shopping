package domain.item;

import domain.CategoryItem;
import exception.NotExistItemException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
@Table(name = "item")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public void addQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeQuantity(int quantity) {
        int resultQuantity = this.stockQuantity - quantity;
        if(resultQuantity < 0) {
            throw new NotExistItemException("need stock item");
        }

        this.stockQuantity = resultQuantity;
    }
}
