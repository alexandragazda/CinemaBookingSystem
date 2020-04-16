package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.*;
import com.cinema.cinemaserver.domain.dtos.BookingDTO;
import com.cinema.cinemaserver.domain.dtos.BookingInfoDTO;
import com.cinema.cinemaserver.domain.enums.Technology;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.BookingRepository;
import com.cinema.cinemaserver.service.*;
import com.cinema.cinemaserver.utils.BookingUtils;
import com.cinema.cinemaserver.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImplementation implements BookingService {
   @Autowired
   private BookingRepository bookingRepository;
   @Autowired
   private ShowtimeService showtimeService;
   @Autowired
   private UserService userService;
   @Autowired
   private TicketTypeService ticketTypeService;
   @Autowired
   private TicketService ticketService;

   @Autowired
   private Validator<BookingDTO> validatorDTO;

   @Autowired
   private BookingUtils bookingUtils;

   @Autowired
   private Converters converters;

   private static final LocalDate today=LocalDate.now();

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking findByID(Integer ID) {
        if(bookingRepository.findById(ID).isPresent()) return bookingRepository.findById(ID).get();
        return null;
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
    public List<Booking> findAllByUserEmail(String userEmail) {
        return bookingRepository.findAllByUserEmail(userEmail);
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
        if(user!=null) booking = converters.convertFromBookingDTOToBookingWithUser(bookingDTO,user,showtime);
        else booking = converters.convertFromBookingDTOToBookingWithCustomer(bookingDTO,showtime);

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
                    throw new ServiceException("The seat is already taken!");
                }

                TicketType ticketType=null;
                double price=0.0;
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
        tickets.forEach(x-> ticketService.save(x));

        bookingUtils.sendBookingEmail(booking.getID());

        return booking;
    }

    @Override
    public List<BookingInfoDTO> findFirstExpiredBookings(String userEmail) {
        if(userService.findByEmail(userEmail)==null)
            throw new ServiceException("Cannot find the specified user!");

        List<Booking> bookings=findAllByUserEmail(userEmail); //all the bookings made by the specified user

        //first comparison after showtime date
        Comparator<Booking> comparator = Comparator.comparing(b-> b.getShowtime().getDate());
        //second comparison after showtime time
        comparator = comparator.thenComparing(b->b.getShowtime().getTime()).reversed();

        bookings=bookings
                .stream()
                .filter(x-> (x.getShowtime().getDate().isBefore(today) )
                        ||(x.getShowtime().getDate().isEqual(today)
                        && x.getShowtime().getTime().isBefore(LocalTime.now()))) //get all the expired bookings
                .sorted(comparator)
                .limit(5) //get the first 5 bookings
                .collect(Collectors.toList());

        return converters.convertFromBookingsToBookingInfoDTOS(bookings);
    }

    @Override
    public List<BookingInfoDTO> findValidBookings(String userEmail) {
        if(userService.findByEmail(userEmail)==null)
            throw new ServiceException("Cannot find the specified user!");

        List<Booking> bookings=findAllByUserEmail(userEmail); //all the bookings made by the specified user

        //first comparison after showtime date
        Comparator<Booking> comparator = Comparator.comparing(b-> b.getShowtime().getDate());
        //second comparison after showtime time
        comparator = comparator.thenComparing(b->b.getShowtime().getTime());

        bookings=bookings
                .stream()
                .filter(x-> (x.getShowtime().getDate().isAfter(today))
                        ||(x.getShowtime().getDate().isEqual(today)
                        && x.getShowtime().getTime().isAfter(LocalTime.now()))) //get all the valid bookings
                .sorted(comparator) //sort the bookings after date and time ascending
                .collect(Collectors.toList());

        return converters.convertFromBookingsToBookingInfoDTOS(bookings);
    }

    @Override
    public void delete(Integer ID) {
        if(findByID(ID) == null)
            throw new ServiceException("The booking was not found!");

        bookingRepository.deleteById(ID); //all the tickets corresponding to the booking will be deleted, as well
    }
}
