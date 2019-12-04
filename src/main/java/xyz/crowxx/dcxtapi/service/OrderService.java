package xyz.crowxx.dcxtapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.crowxx.dcxtapi.controller.VO.FoodModelVo;
import xyz.crowxx.dcxtapi.model.FoodModel;
import xyz.crowxx.dcxtapi.model.OrderModel;
import xyz.crowxx.dcxtapi.repository.FoodModelRepository;
import xyz.crowxx.dcxtapi.repository.OrderRepository;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Resource
    OrderRepository orderRepository;
    @Resource
    FoodModelRepository foodModelRepository;
    public Long addOrder(List<FoodModelVo> foodModelVos,Long MEUNN_UMBER) {
        String foodsId = UUID.randomUUID().toString().replace("-","");
        String ordernum = "WD" + Long.toString(System.currentTimeMillis()).replace("-","");
        OrderModel orderModel = new OrderModel();
        orderModel.setFoodsId(foodsId);
        orderModel.setOrdertime(Long.toString(System.currentTimeMillis()));
        orderModel.setTaken(false);
        orderModel.setMeunnumber("A" + MEUNN_UMBER);
        orderModel.setOrderinfo(ordernum);
        orderModel.setOrdernum(ordernum);
        orderRepository.save(orderModel);
        OrderModel om = orderRepository.findOrderModelByOrdernum(ordernum);
        Long resultId = om.getOrderid();
        for (FoodModelVo vo : foodModelVos) {
            FoodModel foodModel = new FoodModel();
            foodModel.setName(vo.getName());
            foodModel.setNumber(vo.getNumber());
            foodModel.setPrice(vo.getPrice());
            foodModel.setSum(vo.getSum());
            foodModel.setFoodsId(foodsId);
            foodModel.setOrderid(resultId);
            foodModelRepository.save(foodModel);
        }
        return resultId;
    }

    public OrderModel findOrderModelByOrderid(Long id) {
        return orderRepository.findOrderModelByOrderid(id);
    }
}
