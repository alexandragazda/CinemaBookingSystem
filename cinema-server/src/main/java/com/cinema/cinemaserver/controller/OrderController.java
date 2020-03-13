package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.PlacedOrder;
import com.cinema.cinemaserver.domain.PlacedOrderItem;
import com.cinema.cinemaserver.domain.dtos.OrderDTO;
import com.cinema.cinemaserver.domain.utils.OrderItem;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.PlacedOrderItemService;
import com.cinema.cinemaserver.service.PlacedOrderService;
import com.cinema.cinemaserver.service.ServiceException;
import com.cinema.cinemaserver.utils.OrderUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    private PlacedOrderService placedOrderService;

    @Autowired
    private PlacedOrderItemService placedOrderItemService;

    @Autowired
    private OrderUtils orderUtils;

    @GetMapping("/")
    public String welcome(){

        try {
            List<OrderItem> l=new ArrayList();
            l.add(new OrderItem(18,3));
            OrderDTO orderDTO = new OrderDTO(6,null,"tartageorge@yahoo.com","George","Tarta",l,30.0, LocalTime.of(20,10));
            placedOrderService.save(orderDTO);
//            PlacedOrderItem placedOrderItem=new PlacedOrderItem(0,null,null);
//            placedOrderItemService.save(placedOrderItem);
        }
        catch (ValidationException e){
            System.out.println(e);
        }
        catch (ServiceException e){
            System.out.println(e);
        }

        return "welcome";
    }

    @PostMapping("/orders")
    public ResponseEntity<Integer> save(@RequestBody OrderDTO orderDTO) {
        try {
            PlacedOrder placedOrder=placedOrderService.save(orderDTO);

            return ResponseEntity.ok().body(placedOrder.getID());
        }
        catch (ValidationException ex){
            System.out.println(ex);
            return ResponseEntity.status(422).body(-1); //validation error
        }
        catch (ServiceException ex){
            System.out.println(ex);
            return ResponseEntity.status(400).body(-1); //wrong data
        }
    }

//    @PostMapping("/orderEmail")
//    public ResponseEntity<String> orderEmail(@RequestBody ObjectNode objectNode){
//
//        Gson gson=new Gson();
//
//        Integer code= objectNode.get("code").asInt();
//
//        try{
//            orderUtils.sendOrderEmail(code);
//            return ResponseEntity.accepted().body(gson.toJson("",String.class)); //the email was sent successfully
//        }
//        catch (Exception ex){
//            return ResponseEntity.status(500).body(gson.toJson(ex.getMessage(),String.class)); //the email was not sent
//        }
//    }
}
