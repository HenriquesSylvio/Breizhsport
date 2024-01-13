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
public class Orders extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> products;

    // Constructors
    public Orders() {
        // this.orderDate = LocalDateTime.now();
    }

    public Orders(User user) {
        this.user = user;
    }

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> cartItems) {
        this.products = cartItems;
    }

    public UUID getId() {
        return id;
    }
}

