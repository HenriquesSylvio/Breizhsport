package yakak.api.models.entities;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductOrder extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    // Constructors, getters, setters, and other methods

    // Constructors
    public ProductOrder() {
    }

    public ProductOrder(Orders orders, Product product, Integer quantity) {
        this.order = orders;
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and setters

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }
}
