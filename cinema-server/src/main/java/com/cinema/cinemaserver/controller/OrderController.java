package com.cinema.cinemaserver.controller;

import com.cinema.cinemaserver.domain.PlacedOrder;
import com.cinema.cinemaserver.domain.PlacedOrderItem;
import com.cinema.cinemaserver.domain.dtos.OrderDTO;
import com.cinema.cinemaserver.domain.dtos.OrderInfoDTO;
import com.cinema.cinemaserver.domain.utils.OrderItem;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.OrderUtils;
import com.cinema.cinemaserver.utils.UserUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
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

//        try {
////            List<OrderItem> l=new ArrayList();
////            l.add(new OrderItem(18,3));
////            OrderDTO orderDTO = new OrderDTO(6,null,"tartageorge@yahoo.com","George","Tarta",l,30.0, LocalTime.of(20,10));
////            placedOrderService.save(orderDTO);
////            PlacedOrderItem placedOrderItem=new PlacedOrderItem(0,null,null);
////            placedOrderItemService.save(placedOrderItem);
//        }
//        catch (ValidationException e){
//            System.out.println(e);
//        }
//        catch (ServiceException e){
//            System.out.println(e);
//        }

//        List<OrderItem> list=new ArrayList<>();
//        list.add(new OrderItem(5,3));
//        placedOrderService.save(new OrderDTO(18, "alexandragazda@yahoo.com","alexandragazda@yahoo.com","Alexandra","Gazda",list,39.0,LocalTime.of(11,50)));
        placedOrderService.findFirstExpiredOrders("alexandragazda@yahoo.com").forEach(x-> System.out.println(x.getOrderID()));
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

    @GetMapping("/orders")
    public List<PlacedOrder> orders() {
        return placedOrderService.findAll();
    }

    @GetMapping("/expiredOrders")
    public ResponseEntity<List<OrderInfoDTO>> getExpiredOrders(@RequestHeader(value = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims decoded= UserUtils.decodeJWT(token);

        List<OrderInfoDTO> expiredOrders = placedOrderService.findFirstExpiredOrders(decoded.getSubject());
        return ResponseEntity.ok().body(expiredOrders);
    }
}
