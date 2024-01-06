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
public class CartItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Item item;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    // @NotNull(message = "Unit price is required")
    // @Column(name = "unit_price")
    // private Double unitPrice;

    // @NotNull(message = "Total price is required")
    // @Column(name = "total_price")
    // private Double totalPrice;

    // Constructors, getters, setters, and other methods

    // Constructors
    public CartItem() {
    }

    public CartItem(Cart cart, Item item, Integer quantity, Double unitPrice, Double totalPrice) {
        this.cart = cart;
        this.item = item;
        this.quantity = quantity;
        // this.unitPrice = unitPrice;
        // this.totalPrice = totalPrice;
    }

    // Getters and setters

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart order) {
        this.cart = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // public Double getUnitPrice() {
    //     return unitPrice;
    // }

    // public void setUnitPrice(Double unitPrice) {
    //     this.unitPrice = unitPrice;
    // }

    // public Double getTotalPrice() {
    //     return totalPrice;
    // }

    // public void setTotalPrice(Double totalPrice) {
    //     this.totalPrice = totalPrice;
    // }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
