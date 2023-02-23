package hk.hku.servlet;

import hk.hku.entity.FlightEntity;
import hk.hku.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/showFlight")
public class ShowFlightServlet extends HttpServlet {
    private FlightService flightService = new FlightService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //control phase use service phase to get data
        List<FlightEntity> byAll = flightService.getByAll();
        // forward data to jsp
        req.setAttribute("flights",byAll);
        req.getRequestDispatcher("/show.jsp").forward(req,resp);
    }
}
