package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.*;
import com.cinema.cinemaserver.domain.dtos.BookingInfoDTO;
import com.cinema.cinemaserver.domain.dtos.OrderDTO;
import com.cinema.cinemaserver.domain.dtos.OrderInfoDTO;
import com.cinema.cinemaserver.domain.dtos.PlacedOrderItemDTO;
import com.cinema.cinemaserver.domain.utils.OrderItem;
import com.cinema.cinemaserver.domain.validator.ValidationException;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.PlacedOrderRepository;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.BookingUtils;
import com.cinema.cinemaserver.utils.Converters;
import com.cinema.cinemaserver.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacedOrderServiceImplementation implements PlacedOrderService {
    @Autowired
    private PlacedOrderRepository placedOrderRepository;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConcessionService concessionService;

    @Autowired
    private PlacedOrderItemService placedOrderItemService;

    @Autowired
    private Validator<OrderDTO> validatorDTO;

    @Autowired
    private OrderUtils orderUtils;

    @Autowired
    private Converters converters;

    @Override
    public List<PlacedOrder> findAll() {
        return placedOrderRepository.findAll();
    }

    @Override
    public PlacedOrder save(PlacedOrder placedOrder) {
        placedOrderRepository.save(placedOrder);

        return placedOrder;
    }

    @Override
    public PlacedOrder save(OrderDTO orderDTO) {
        validatorDTO.validate(orderDTO); //validates the given order DTO

        Showtime showtime = showtimeService.findById(orderDTO.getShowtimeID());
        if(showtime == null) throw new ServiceException("Cannot find showtimeID!"); //the given showtimeID does not exist

        String userEmail=orderDTO.getUserEmail();
        User user=null;
        if(userEmail!=null){
            user=userService.findByEmail(userEmail);
            if(user==null) throw new ServiceException("Cannot find the specified user!"); //the given user email does not exist
        }

        Double totalPrice=0.0;
        for (OrderItem o: orderDTO.getOrderItems()
             ) {
            Concession concession = concessionService.findByID(o.getConcessionID());
            if(concession==null) throw new ValidationException("Concession cannot be null!");
            totalPrice += o.getQty() * concession.getPrice();
        }
        if(totalPrice.compareTo(orderDTO.getTotalPrice()) !=0)
            throw  new ServiceException("Total price is incorrect!");

        if(orderDTO.getPickUpTime().compareTo(showtime.getTime().minusMinutes(30)) == -1 || orderDTO.getPickUpTime().compareTo(showtime.getTime().minusMinutes(10)) ==1)
            throw new ServiceException("Pick up time is incorrect!");

        PlacedOrder placedOrder;
        if(user!=null) {
//            placedOrder = new PlacedOrder(orderDTO.getTotalPrice(), orderDTO.getPickUpTime(), user.getID(), user.getFirstName(), user.getLastName(), user, showtime);
              placedOrder = converters.convertFromOrderDTOToOrderWithUser(orderDTO,user,showtime);
        }
        else{
//            placedOrder = new PlacedOrder(orderDTO.getTotalPrice(), orderDTO.getPickUpTime(), orderDTO.getCustomerEmail(), orderDTO.getCustomerFirstName(), orderDTO.getCustomerLastName(), null, showtime);
              placedOrder = converters.convertFromOrderDTOToOrderWithCustomer(orderDTO,showtime);
        }

        save(placedOrder);

        orderDTO.getOrderItems().forEach(x->{
            Concession concession=concessionService.findByID(x.getConcessionID());
            PlacedOrderItem placedOrderItem=new PlacedOrderItem(x.getQty(), placedOrder, concession);
            placedOrderItemService.save(placedOrderItem);
        });

        orderUtils.sendOrderEmail(placedOrder.getID());

        return placedOrder;
    }

    @Override
    public PlacedOrder findByID(Integer ID) {
        if(placedOrderRepository.findById(ID).isPresent()) return placedOrderRepository.findById(ID).get();
        return null;
    }

    @Override
    public List<PlacedOrder> findAllByUserEmail(String userEmail) {
        return placedOrderRepository.findAllByUserEmail(userEmail);
    }

    @Override
    public List<OrderInfoDTO> findFirstExpiredOrders(String userEmail) {
        if(userService.findByEmail(userEmail)==null)
            throw new ServiceException("Cannot find the specified user!");

        LocalDate today=LocalDate.of(2020,3,19); // !!! today

        List<PlacedOrder> orders=findAllByUserEmail(userEmail); //all the orders made by the specified user

        //first comparison after showtime date
        Comparator<PlacedOrder> comparator = Comparator.comparing(o-> o.getShowtime().getDate());
        //second comparison after showtime time
        comparator = comparator.thenComparing(o->o.getShowtime().getTime()).reversed();

        orders=orders
                .stream()
                .filter(x-> (x.getShowtime().getDate().isBefore(today) )
                        ||(x.getShowtime().getDate().isEqual(today)
                        && x.getShowtime().getTime().isBefore(LocalTime.now()))) //get all the expired orders
                .sorted(comparator)
                .limit(5) //get the first 5 orders
                .collect(Collectors.toList());

        return converters.convertFromOrdersToOrderInfoDTOS(orders);
    }

    @Override
    public List<OrderInfoDTO> findValidOrders(String userEmail) {
        if(userService.findByEmail(userEmail)==null)
            throw new ServiceException("Cannot find the specified user!");

        LocalDate today=LocalDate.of(2020,3,19); // !!! today

        List<PlacedOrder> orders=findAllByUserEmail(userEmail); //all the orders made by the specified user

        //first comparison after showtime date
        Comparator<PlacedOrder> comparator = Comparator.comparing(o-> o.getShowtime().getDate());
        //second comparison after showtime time
        comparator = comparator.thenComparing(o->o.getShowtime().getTime());

        orders=orders
                .stream()
                .filter(x-> (x.getShowtime().getDate().isAfter(today) )
                        ||(x.getShowtime().getDate().isEqual(today)
                        && x.getShowtime().getTime().isAfter(LocalTime.now()))) //get all the valid orders
                .sorted(comparator) //sort the orders after date and time ascending
                .collect(Collectors.toList());

        return converters.convertFromOrdersToOrderInfoDTOS(orders);
    }

    @Override
    public void delete(Integer ID) {
        if(placedOrderRepository.findById(ID) == null)
            throw new ServiceException("The order was not found!");

        placedOrderRepository.deleteById(ID); //all the placed order items corresponding to the order will be deleted, as well
    }
}
