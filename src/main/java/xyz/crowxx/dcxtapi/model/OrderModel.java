package xyz.crowxx.dcxtapi.model;
import lombok.Data;
import javax.persistence.*;
@Entity
@Data
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;
    private boolean taken;
    private String orderinfo;
    private String ordertime;
    private String ordernum;
    private String meunnumber;
    private String foodsId;
}
