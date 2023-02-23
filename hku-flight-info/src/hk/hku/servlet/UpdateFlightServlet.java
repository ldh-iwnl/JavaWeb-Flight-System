package hk.hku.servlet;

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
import java.text.ParseException;
import java.util.Date;

@WebServlet("/updateFlight")
public class UpdateFlightServlet  extends HttpServlet {
    private FlightService flightService = new FlightService();
    /**
     * when user clicks edit button -- need to get flight information
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(idStr==null || idStr==""){
            //jump to error page
            req.setAttribute("errMsg","id is null");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        try{
            Integer id = Integer.parseInt(idStr);
            FlightEntity flightEntity = flightService.getFlightById(id);
            if(flightEntity==null){
                //jump to error page
                req.setAttribute("errMsg","flight not found");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
            req.setAttribute("flight",flightEntity);
            req.getRequestDispatcher("/updateFlight.jsp").forward(req,resp);
        }catch (Exception e){
            req.setAttribute("errMsg","system error!");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }
    /**
     * when user clicks update button -- receive updated flight information
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (StringUtils.isEmpty(idStr)) {
            //jump to error page
            req.setAttribute("errMsg", "id is null");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }
        Integer id = Integer.parseInt(idStr);
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
        FlightEntity flightEntity = new FlightEntity(id, flightId, company, departureAirport, arriveAirport, sqlDepartureTime, sqlArriveTime, model, 0);
        int result = flightService.updateFlight(flightEntity);
        if(result<=0){
            //jump to error page
            req.setAttribute("errMsg","update failed");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect("showFlight");


    }
}
