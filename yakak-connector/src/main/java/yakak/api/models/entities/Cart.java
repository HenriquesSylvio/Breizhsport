package yakak.api.models.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cart extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // @Column(name = "order_date")
    // private LocalDateTime orderDate;

    // @NotNull(message = "Total amount is required")
    // @Column(name = "total_amount")
    // private Double totalAmount;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    // Constructors
    public Cart() {
        // this.orderDate = LocalDateTime.now();
    }

    public Cart(User user, Double totalAmount) {
        this.user = user;
        // this.orderDate = LocalDateTime.now();
        // this.totalAmount = totalAmount;
    }

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // public LocalDateTime getOrderDate() {
    //     return orderDate;
    // }

    // public void setOrderDate(LocalDateTime orderDate) {
    //     this.orderDate = orderDate;
    // }

    // public Double getTotalAmount() {
    //     return totalAmount;
    // }

    // public void setTotalAmount(Double totalAmount) {
    //     this.totalAmount = totalAmount;
    // }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

