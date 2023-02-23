package hk.hku.servlet;

import hk.hku.dao.FlightDao;
import hk.hku.entity.FlightEntity;
import hk.hku.service.FlightService;
import hk.hku.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

@WebServlet("/insertFlight")
public class InsertFlightServlet extends HttpServlet {
    private FlightService flightService = new FlightService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/insertFlight.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flightId = req.getParameter("flightId");
        String company = req.getParameter("company");
        String departureAirport = req.getParameter("departureAirport");
        String arriveAirport = req.getParameter("arriveAirport");
        String departureTime = req.getParameter("departureTime");
        //change string date to sql date
        Date sqlDepartureTime = null;
        try {
            sqlDepartureTime = DateUtils.stringToDate(departureTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String arriveTime = req.getParameter("arriveTime");
        //change string date to sql date
        Date sqlArriveTime = null;
        try {
            sqlArriveTime = DateUtils.stringToDate(arriveTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String model = req.getParameter("model");
        FlightEntity flightEntity = new FlightEntity(flightId, company, departureAirport, arriveAirport, sqlDepartureTime, sqlArriveTime, model, 0);
        int result = flightService.insertFlight(flightEntity);
        if(result<=0){
            //jump to error page
            req.setAttribute("errMsg","update failed");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect("showFlight");


    }
}
