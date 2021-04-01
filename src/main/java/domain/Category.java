package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();
//    private CategoryItem categoryItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany
    private List<Category> child = new ArrayList<>();
}
