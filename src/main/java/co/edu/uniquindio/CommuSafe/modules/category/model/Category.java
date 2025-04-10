package co.edu.uniquindio.CommuSafe.modules.category.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Data
@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Category {

    @EqualsAndHashCode.Include
    @Id
    private ObjectId id;

    private String name;

    private String icon;

    private String description;

    @Field(name = "createdAt")
    private LocalDate createdAt;

    public Category(){
    }

    public Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
}
