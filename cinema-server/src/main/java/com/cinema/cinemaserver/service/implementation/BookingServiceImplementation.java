package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.Booking;
import com.cinema.cinemaserver.domain.Screen;
import com.cinema.cinemaserver.repository.BookingRepository;
import com.cinema.cinemaserver.service.BookingService;
import com.cinema.cinemaserver.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImplementation implements BookingService {
   @Autowired
   private BookingRepository bookingRepository;

   @Autowired
   private ScreenService screenService;

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findAllByScreenIDAndDateAndTime(Integer screenID, LocalDate date, LocalTime time) {
        return bookingRepository.findAllByScreenIDAndDateAndTime(screenID, date, time);
    }

    @Override
     public List<List<Integer>> stateOfSeats(Integer screenID, LocalDate date, LocalTime time) {
        List<List<Integer>> stateOfSeats=new ArrayList<>();

        Screen screen=screenService.findById(screenID);

        List<Integer> cols=new ArrayList<>();
        for(int i=0;i<screen.getNrCols();i++) cols.add(0);
        for(int i=0;i<screen.getNrRows();i++){
            stateOfSeats.add(cols);
        }

        List<Booking> bookings=findAllByScreenIDAndDateAndTime(screenID,date,time);
        bookings.forEach(x->{
            String seats=x.getSeats();
            String[] rowscols=seats.split(";");
            for(int i=0;i<rowscols.length;i++){
                String[] rowscolsParsed=rowscols[i].split(":");
                int row=Integer.parseInt(rowscolsParsed[0]);
                String[] colsArray=rowscolsParsed[1].split(",");

                List<Integer> returnedCols=new ArrayList<>();
                for(int k=0;k<screen.getNrCols();k++){
                    returnedCols.add(0);
                }
                for(int l=0;l<colsArray.length;l++) {
                    returnedCols.set(Integer.parseInt(colsArray[l]),1);
                }

                stateOfSeats.set(row,returnedCols);
            }
        });

        System.out.print("  ");
        for(int i=0;i<screen.getNrCols();i++) System.out.print(i + " ");
        System.out.println();
        for(int i=0;i<screen.getNrRows();i++){
            System.out.print(i+" ");
            for(int j=0;j<screen.getNrCols();j++){
                System.out.print(stateOfSeats.get(i).get(j)+ " ");
            }
            System.out.println();
        }

        return stateOfSeats;
    }
}
