package xyz.crowxx.dcxtapi.controller;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import xyz.crowxx.dcxtapi.controller.VO.FoodModelVo;
import xyz.crowxx.dcxtapi.model.FoodModel;
import xyz.crowxx.dcxtapi.model.OrderModel;
import xyz.crowxx.dcxtapi.repository.OrderRepository;
import xyz.crowxx.dcxtapi.service.FoodModelService;
import xyz.crowxx.dcxtapi.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    FoodModelService foodModelService;
    @Resource
    OrderRepository orderRepository;
    /*    private Long order_id;
    private boolean taken;
    private String orderinfo;
    private String ordertime;
    private String ordernum;
    private String meunnumber;*/
    public Long MEUNN_UMBER = 1L;
    @PostMapping("/order")
    public Object addOrder(@RequestBody Orders orders){
        List<FoodModelVo> foodModelVos = orders.getOrders();
        Long id =  orderService.addOrder(foodModelVos,MEUNN_UMBER);
        MEUNN_UMBER+=1;
        Para para = new Para();
        para.setError(0);
        para.setOrder_id(id);
        return para;
    }
    @GetMapping("/order")
    public Object findOrderById(@RequestParam("order_id")Long id){
        OrderModel orderModel = orderService.findOrderModelByOrderid(id);
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(orderModel,orderVo);
        List<FoodModel> list = foodModelService.findFoodsByOrderId(orderModel.getOrderid());
        List<FmVo> fmVos = new ArrayList<>();
        for (FoodModel model : list) {
            FmVo fmVo = new FmVo();
            BeanUtils.copyProperties(model,fmVo);
            fmVo.setNum(model.getNumber());
            fmVos.add(fmVo);
        }
        OrderPara orderPara = new OrderPara();
        orderPara.setOrder_id(Long.toString(orderModel.getOrderid()));
        orderPara.setFoods(fmVos);
        orderPara.setMeunnumber(orderModel.getMeunnumber());
        orderPara.setOrderinfo(orderModel.getOrderinfo());
        orderPara.setOrdernum(orderModel.getOrdernum());
        orderPara.setOrdertime(orderModel.getOrdertime());
        orderPara.setTaken(orderModel.isTaken());
        return orderPara;
    }

    @GetMapping("/orderlist")
    public Object findAllOrder(){
        List<OrderModel> orderModels = orderRepository.findAll();
        List<Long> orderids = new ArrayList<>();
        List<OrderListPara> listParas = new ArrayList<>();
        for (OrderModel orderModel : orderModels) {
            Long id = orderModel.getOrderid();
            orderids.add(id);
            OrderListPara orderListPara = new OrderListPara();
            orderListPara.setOrder_id(Long.toString(id));
            listParas.add(orderListPara);
        }
        for (int i = 0;i<orderids.size();i++){
            //得到对应订单的foods
            List<FoodModel> foodModels = foodModelService.findFoodsByOrderId(orderids.get(i));
            //得到对应订单
            OrderModel tmpOM = orderService.findOrderModelByOrderid(orderids.get(i));

            OrderFoodsVo foodsVo = new OrderFoodsVo();
            foodsVo.setName(foodModels.get(0).getName());
            foodsVo.setDate(tmpOM.getOrdertime());
            int money = 0;
            for(int j = 0;j < foodModels.size();j++){
                money += foodModels.get(j).getSum();
            }
            foodsVo.setMoney(money);
            foodsVo.setPrice(money+".00");
            foodsVo.setDescribe("等" + foodModels.size() + "件商品");
            listParas.get(i).setFoods(foodsVo);
            listParas.get(i).setTaken(true);
        }
        RespPara respPara = new RespPara();
        respPara.setList(listParas);
        return respPara;
    }
}
@Data
class RespPara{
    List<OrderListPara> list;
}

@Data
class OrderListPara{
    String order_id;
    OrderFoodsVo foods;
    boolean taken;
}
@Data
class OrderFoodsVo{
    private String name;
    private String describe;
    private String price;
    private String date;
    private int money;
}
@Data
class OrderPara {
   private String order_id;
   private List<FmVo> foods;
   private boolean taken;
   private String orderinfo;
   private String ordertime;
   private String ordernum;
   private String meunnumber;
}
@Data
class FmVo{
    private String name;
    private Integer price;
    private Integer num;
}
@Data
class OrderVo{
    private Long orderid;
    private boolean taken;
    private String orderinfo;
    private String ordertime;
    private String ordernum;
    private String meunnumber;
}
@Data
class Orders{
    List<FoodModelVo> orders;
}
@Data
class Para{

    /**
     * error : 0
     * order_id : 1
     */

    private int error;
    private Long order_id;

}

