package hk.hku.service;

import hk.hku.dao.FlightDao;
import hk.hku.entity.FlightEntity;

import java.util.List;

public class FlightService {
    // use Dao to access database
    private FlightDao flightDao = new FlightDao();

    //get all flight information
    public List<FlightEntity> getByAll(){
        return flightDao.getByAll();
    }

    public int deleteFlightById(Integer id){
        return flightDao.deleteFlightById(id);
    }

    public FlightEntity getFlightById(Integer id){
        return flightDao.getFlightById(id);
    }
    public int updateFlight(FlightEntity flightEntity){
        return flightDao.updateFlight(flightEntity);
    }

    public int insertFlight(FlightEntity flightEntity){
        return flightDao.insertFlight(flightEntity);
    }

    public int updateDelete(Integer id){
        return flightDao.updateDelete(id);
    }

}

