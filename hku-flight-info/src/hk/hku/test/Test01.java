package hk.hku.test;

import hk.hku.dao.FlightDao;
import hk.hku.entity.FlightEntity;

import java.util.Date;

public class Test01 {
    public static void main(String[] args) {
        FlightDao flightDao = new FlightDao();
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(1);
        flightEntity.setFlightId("CA123");
        flightEntity.setCompany("Cathay Pacific");
        flightEntity.setDepartureAirport("Hong Kong");
        flightEntity.setArriveAirport("London");
        flightEntity.setDepartureTime(new Date());
        flightEntity.setArriveTime(new Date());
        flightEntity.setModel("Boeing 777");
        flightEntity.setIsDelete(0);
        int i = flightDao.updateFlight(flightEntity);
        System.out.println(i);
    }
}
