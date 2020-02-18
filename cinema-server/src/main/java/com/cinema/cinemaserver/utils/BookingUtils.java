//package com.cinema.cinemaserver.utils;
//
//import com.cinema.cinemaserver.domain.Screen;
//import com.cinema.cinemaserver.service.BookingService;
//import com.cinema.cinemaserver.service.ScreenService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookingUtils {
//    @Autowired
//    private BookingService bookingService;
//
//    @Autowired
//    private ScreenService screenService;
//
//    public List<List<Integer>> stateOfSeats(Integer screenID, LocalDate date, LocalTime time) {
//        List<List<Integer>> stateOfSeats=new ArrayList<>();
//        Screen screen=screenService.findById(screenID);
//        for(int i=0;i<screen.getNrRows();i++){
//            for(int j=0;j<screen.getNrCols();j++){
//                System.out.println(stateOfSeats.get(i).get(j)+" ");
//            }
//            System.out.println();
//        }
//
//        return stateOfSeats;
//    }
//}
