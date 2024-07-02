package online.shopping.system.cart_service.repository;

import online.shopping.system.cart_service.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem, Integer>, JpaSpecificationExecutor<CartItem> {
}
