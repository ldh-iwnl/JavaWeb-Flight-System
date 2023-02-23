package hk.hku.dao;

import hk.hku.entity.FlightEntity;
import hk.hku.utils.HkuJdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDao {

    //get all flight information
    public List<FlightEntity> getByAll(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlightEntity> flightEntityList = new ArrayList<>();
        try {
            //get connection
            connection = HkuJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from kyle_flight where is_delete=0");
            //execute query
            resultSet=preparedStatement.executeQuery();
            //make a arrayList to store data

            while(resultSet.next()!=false){
                //read data from database
                Integer id = resultSet.getInt("id");
                String flightId = resultSet.getString("flight_id");
                String company = resultSet.getString("company");
                String departureAirport = resultSet.getString("departure_airport");
                String arriveAirport = resultSet.getString("arrive_airport");
                Date departureTime = resultSet.getDate("departure_time");
                Date arriveTime = resultSet.getDate("arrive_time");
                String model = resultSet.getString("model");
                Integer isDelete = resultSet.getInt("is_delete");
                //make a new FlightEntity object
                FlightEntity flightEntity = new FlightEntity(id,flightId,company,departureAirport,
                        arriveAirport,departureTime,arriveTime,model,isDelete);
                //add object to arrayList
                flightEntityList.add(flightEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            //close connection
            HkuJdbcUtils.closeConnection(resultSet,preparedStatement,connection);

        }


        return flightEntityList;
    }

    /**
     * get flight info by id
     * @param targetId
     * @return
     */
    public FlightEntity getFlightById(Integer targetId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //get connection
            connection = HkuJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from kyle_flight where id=?");
            preparedStatement.setInt(1,targetId);
            //execute query
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()!=false){
                //read data from database
                Integer id = resultSet.getInt("id");
                String flightId = resultSet.getString("flight_id");
                String company = resultSet.getString("company");
                String departureAirport = resultSet.getString("departure_airport");
                String arriveAirport = resultSet.getString("arrive_airport");
                Date departureTime = resultSet.getDate("departure_time");
                Date arriveTime = resultSet.getDate("arrive_time");
                String model = resultSet.getString("model");
                Integer isDelete = resultSet.getInt("is_delete");
                //make a new FlightEntity object
                FlightEntity flightEntity = new FlightEntity(id,flightId,company,departureAirport,
                        arriveAirport,departureTime,arriveTime,model,isDelete);
                return flightEntity;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            //close connection
            HkuJdbcUtils.closeConnection(resultSet,preparedStatement,connection);

        }
    }

    /**
     *  delete flight by id
     *  update/insert/delete need to add transaction
     * @param id
     * @return
     */
    public int deleteFlightById(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlightEntity> flightEntityList = new ArrayList<>();
        try {
            //get connection
            connection = HkuJdbcUtils.getConnection();
            //add transaction
            HkuJdbcUtils.beginTransaction(connection);
            preparedStatement = connection.prepareStatement("delete from kyle_flight where id = ?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            //commit transaction
            HkuJdbcUtils.commitTransaction(connection);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            //rollback transaction
            HkuJdbcUtils.rollBackTransaction(connection);
            return 0;
        }finally {
            //close connection
            HkuJdbcUtils.closeConnection(preparedStatement,connection);

        }
    }

    /**
     * update flight info by id
     * @param flightEntity
     * @return
     */
    public int updateFlight(FlightEntity flightEntity){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlightEntity> flightEntityList = new ArrayList<>();
        try {
            //get connection
            connection = HkuJdbcUtils.getConnection();
            //add transaction
            HkuJdbcUtils.beginTransaction(connection);
            preparedStatement = connection.prepareStatement("UPDATE `kyledb`.`kyle_flight` " +
                    "SET `flight_id` = ?, `company` = ?, `departure_airport` " +
                    "= ?, `arrive_airport` = ?, `departure_time` " +
                    "= ?, `arrive_time` = ?, `model` = ?" +
                    ", `is_delete` = 0 WHERE `id` = ?;");
            preparedStatement.setString(1, flightEntity.getFlightId());
            preparedStatement.setString(2, flightEntity.getCompany());
            preparedStatement.setString(3, flightEntity.getDepartureAirport());
            preparedStatement.setString(4, flightEntity.getArriveAirport());
            preparedStatement.setDate(5, new Date(flightEntity.getDepartureTime().getTime()));
            preparedStatement.setDate(6, new Date(flightEntity.getArriveTime().getTime()));
            preparedStatement.setString(7, flightEntity.getModel());
            preparedStatement.setInt(8, flightEntity.getId());
            int result = preparedStatement.executeUpdate();
            //commit transaction
            HkuJdbcUtils.commitTransaction(connection);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            //rollback transaction
            HkuJdbcUtils.rollBackTransaction(connection);
            return 0;
        }finally {
            //close connection
            HkuJdbcUtils.closeConnection(preparedStatement,connection);
        }
    }

    public int insertFlight(FlightEntity flightEntity){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlightEntity> flightEntityList = new ArrayList<>();
        try {
            //get connection
            connection = HkuJdbcUtils.getConnection();
            //add transaction
            HkuJdbcUtils.beginTransaction(connection);

            //INSERT INTO `kyledb`.`kyle_flight` (`id`, `flight_id`, `company`, `departure_airport`, `arrive_airport`, `departure_time`, `arrive_time`, `model`, `is_delete`) VALUES (1, 'CA123', 'Cathay Pacific', 'Hong Kong', 'London', '2023-02-22 00:00:00', '2023-02-22 00:00:00', 'Boeing 777', 0);
            preparedStatement = connection.prepareStatement("INSERT INTO `kyledb`.`kyle_flight` (`id`, `flight_id`, " +
                    "`company`, `departure_airport`, `arrive_airport`, `departure_time`, `arrive_time`, " +
                    "`model`, `is_delete`) VALUES (null, ?, ?, ?, ?, ?, ?, ?, 0);");
            preparedStatement.setString(1, flightEntity.getFlightId());
            preparedStatement.setString(2, flightEntity.getCompany());
            preparedStatement.setString(3, flightEntity.getDepartureAirport());
            preparedStatement.setString(4, flightEntity.getArriveAirport());
            preparedStatement.setDate(5, new Date(flightEntity.getDepartureTime().getTime()));
            preparedStatement.setDate(6, new Date(flightEntity.getArriveTime().getTime()));
            preparedStatement.setString(7, flightEntity.getModel());
            int result = preparedStatement.executeUpdate();
            //commit transaction
            HkuJdbcUtils.commitTransaction(connection);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            //rollback transaction
            HkuJdbcUtils.rollBackTransaction(connection);
            return 0;
        }finally {
            //close connection
            HkuJdbcUtils.closeConnection(preparedStatement,connection);
        }
    }

    public int updateDelete(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlightEntity> flightEntityList = new ArrayList<>();
        try {
            //get connection
            connection = HkuJdbcUtils.getConnection();
            //add transaction
            HkuJdbcUtils.beginTransaction(connection);
            preparedStatement = connection.prepareStatement("UPDATE `kyledb`.`kyle_flight` " +
                    "SET `is_delete` = ? WHERE `id` = ?;");

            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, id);

            int result = preparedStatement.executeUpdate();
            //commit transaction
            HkuJdbcUtils.commitTransaction(connection);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            //rollback transaction
            HkuJdbcUtils.rollBackTransaction(connection);
            return 0;
        }finally {
            //close connection
            HkuJdbcUtils.closeConnection(preparedStatement,connection);
        }
    }
}
