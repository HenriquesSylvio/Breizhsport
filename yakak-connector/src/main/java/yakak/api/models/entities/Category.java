package yakak.api.models.entities;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Category extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @NotNull(message = "name is required")
    private Integer name;

    private String description;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}
