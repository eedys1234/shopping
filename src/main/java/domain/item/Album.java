package domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@DiscriminatorValue("A")
@Entity
public class Album extends Item {

    private String artist;

    private String etc;
}
