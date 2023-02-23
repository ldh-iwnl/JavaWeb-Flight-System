package hk.hku.entity;

import java.util.Date;

public class FlightEntity {
    /**
     * CREATE TABLE `kyle_flight` (
     *   `id` int NOT NULL AUTO_INCREMENT COMMENT 'id列',
     *   `flight_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '航号',
     *   `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '航空公司',
     *   `departure_airport` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出发机场',
     *   `arrive_airport` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '达到机场',
     *   `departure_time` datetime DEFAULT NULL COMMENT '出发时间',
     *   `arrive_time` datetime DEFAULT NULL COMMENT '到达时间',
     *   `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机型',
     *   `is_delete` int DEFAULT NULL COMMENT '是否隐藏0显示 1隐藏',
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
     */
    private Integer id;
    private String flightId;
    private String company;
    private String departureAirport;
    private String arriveAirport;
    private Date departureTime;
    private Date arriveTime;
    private String model;
    private Integer isDelete;

    public FlightEntity() {
    }

    public FlightEntity(String flightId, String company, String departureAirport, String arriveAirport, Date departureTime, Date arriveTime, String model, Integer isDelete) {
        this.flightId = flightId;
        this.company = company;
        this.departureAirport = departureAirport;
        this.arriveAirport = arriveAirport;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.model = model;
        this.isDelete = isDelete;
    }

    public FlightEntity(Integer id, String flightId, String company, String departureAirport, String arriveAirport, Date departureTime, Date arriveTime, String model, Integer isDelete) {
        this.id = id;
        this.flightId = flightId;
        this.company = company;
        this.departureAirport = departureAirport;
        this.arriveAirport = arriveAirport;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.model = model;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
