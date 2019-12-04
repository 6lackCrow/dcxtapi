package xyz.crowxx.dcxtapi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.crowxx.dcxtapi.model.OrderModel;
import xyz.crowxx.dcxtapi.repository.OrderRepository;
import xyz.crowxx.dcxtapi.service.OrderService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/food/pay")
public class PayController {
    @Resource
    OrderService orderService;
    @Resource
    OrderRepository orderRepository;
    @PostMapping
    public Object isPay(@RequestBody PayPara payPara){
        OrderModel orderModel = orderService.findOrderModelByOrderid(Long.parseLong(payPara.getOrder_id()));
        orderModel.setTaken(true);
        orderRepository.save(orderModel);
        RepPara para = new RepPara();
        para.setError(0);
        para.setOrder_id(Long.parseLong(payPara.getOrder_id()));
        return para;
    }
}


@Data
class PayPara{
    String order_id;
}
@Data
class RepPara{
    Integer error;
    Long order_id;
}
