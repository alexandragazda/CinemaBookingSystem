package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.*;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.enums.Technology;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.BookingRepository;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.BookingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImplementation implements BookingService {
   @Autowired
   private BookingRepository bookingRepository;

   @Autowired
   private ScreenService screenService;

   @Autowired
   private Validator<BookingDTO> validatorDTO;

   @Autowired
   private ShowtimeService showtimeService;

   @Autowired
   private UserService userService;

   @Autowired
   private TicketTypeService ticketTypeService;

   @Autowired
   private TicketService ticketService;

   @Autowired
   private BookingUtils bookingUtils;

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


    @Override
    public List<Booking> findAllByShowtimeID(Integer showtimeID){
        return bookingRepository.findAllByShowtimeID(showtimeID);
    }


    @Override
    public Booking save(BookingDTO bookingDTO) {
        validatorDTO.validate(bookingDTO); //validates the given booking DTO

        Showtime showtime = showtimeService.findById(bookingDTO.getShowtimeID());
        if(showtime == null) throw new ServiceException("Cannot find showtimeID!"); //the given showtimeID does not exist

        String userEmail=bookingDTO.getUserEmail();
        User user=null;
        if(userEmail!=null){
            user=userService.findByEmail(userEmail);
            if(user==null) throw new ServiceException("Cannot find the specified user!"); //the given user email does not exist
        }

        Technology technology=showtime.getTechnology();
        Double totalPrice=bookingDTO.getTotalPrice();
        if(technology.equals(Technology.tec_2D)){
            if(totalPrice!=bookingDTO.getNrChildTickets() * ticketTypeService.findByID(TicketTypeEnum.Child).getPrice2D()+
            bookingDTO.getNrStudentTickets() * ticketTypeService.findByID(TicketTypeEnum.Student).getPrice2D()+
            bookingDTO.getNrAdultTickets() * ticketTypeService.findByID(TicketTypeEnum.Adult).getPrice2D()+
            bookingDTO.getNrRetiredTickets() * ticketTypeService.findByID(TicketTypeEnum.Retired).getPrice2D()){
                throw new ServiceException("Total price is incorrect!"); //the given total price is not correct
            }
        }
        if(technology.equals(Technology.tec_3D)){
            if(totalPrice!=bookingDTO.getNrChildTickets() * ticketTypeService.findByID(TicketTypeEnum.Child).getPrice3D()+
                    bookingDTO.getNrStudentTickets() * ticketTypeService.findByID(TicketTypeEnum.Student).getPrice3D()+
                    bookingDTO.getNrAdultTickets() * ticketTypeService.findByID(TicketTypeEnum.Adult).getPrice3D()+
                    bookingDTO.getNrRetiredTickets() * ticketTypeService.findByID(TicketTypeEnum.Retired).getPrice3D()){
                throw new ServiceException("Total price is incorrect!"); //the given total price is not correct
            }
        }

        bookingDTO.setSelectedSeats(bookingDTO.getSelectedSeats().replace("'",""));

        Booking booking;
        if(user!=null) {
            booking = new Booking(bookingDTO.getSelectedSeats(), bookingDTO.getTotalPrice(), bookingDTO.getNrChildTickets(), bookingDTO.getNrStudentTickets(), bookingDTO.getNrAdultTickets(), bookingDTO.getNrRetiredTickets(), user.getID(), user.getFirstName(), user.getLastName(), showtime, user);
        }
        else{
            booking = new Booking(bookingDTO.getSelectedSeats(), bookingDTO.getTotalPrice(), bookingDTO.getNrChildTickets(), bookingDTO.getNrStudentTickets(), bookingDTO.getNrAdultTickets(), bookingDTO.getNrRetiredTickets(), bookingDTO.getCustomerEmail(), bookingDTO.getCustomerFirstName(), bookingDTO.getCustomerLastName(), showtime, null);
        }

        List<Ticket> tickets=new ArrayList<>();
        int nrChildTickets=booking.getNrChildTickets();
        int nrStudentTickets=booking.getNrStudentTickets();
        int nrAdultTickets=booking.getNrAdultTickets();
        int nrRetiredTickets=booking.getNrRetiredTickets();
        String selectedSeats=booking.getSeats();
        String[] selectedSeatsParsed=selectedSeats.split(";");
        for(int i=0;i<selectedSeatsParsed.length;i++){
            String[] linecols=selectedSeatsParsed[i].split(":");
            int line= Integer.parseInt(linecols[0]);
            String[] cols=linecols[1].split(",");
            for(int j=0;j<cols.length;j++){
                int col=Integer.parseInt(cols[j]);

                if(bookingUtils.stateOfSeats(showtime.getID()).get(line).get(col) == 1) {
                    throw new ServiceException("The seat is already taken");
                }

                TicketType ticketType=null;
                Double price=0.0;
                if(nrChildTickets>0){
                    ticketType=ticketTypeService.findByID(TicketTypeEnum.Child);
                    nrChildTickets--;
                }
                else if(nrStudentTickets>0){
                    ticketType=ticketTypeService.findByID(TicketTypeEnum.Student);
                    nrStudentTickets--;
                }
                else if(nrAdultTickets>0){
                    ticketType=ticketTypeService.findByID(TicketTypeEnum.Adult);
                    nrAdultTickets--;
                }
                else if(nrRetiredTickets>0){
                    ticketType=ticketTypeService.findByID(TicketTypeEnum.Retired);
                    nrRetiredTickets--;
                }

                if(technology.equals(Technology.tec_2D)) price=ticketType.getPrice2D();
                else if(technology.equals(Technology.tec_3D)) price=ticketType.getPrice3D();

                Ticket ticket=new Ticket(price,line,col,ticketType,booking);
                tickets.add(ticket);
            }
        }

        save(booking);
        System.out.println(booking);
        tickets.forEach(x-> {
            ticketService.save(x);
            System.out.println(x);
        });

        bookingUtils.sendBookingEmail(booking.getID());

        return booking;
    }

    @Override
    public Booking findByID(Integer ID) {
        if(bookingRepository.findById(ID).isPresent()) return bookingRepository.findById(ID).get();
        return null;
    }

    @Override
    public void delete() {
        bookingRepository.deleteById(841);
    }
}
