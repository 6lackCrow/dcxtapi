package xyz.crowxx.dcxtapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.dcxtapi.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {
    OrderModel findOrderModelByOrdernum(String ordernum);
    OrderModel findOrderModelByOrderid(Long id);
}
