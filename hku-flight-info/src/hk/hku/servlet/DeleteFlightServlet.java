package hk.hku.servlet;

import hk.hku.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/deleteFlight")
public class DeleteFlightServlet extends HttpServlet {
    private FlightService flightService = new FlightService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id==null || id==""){
            //jump to error page
            req.setAttribute("errMsg","id is null");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        try{
            int i = Integer.parseInt(id);
            int result = flightService.updateDelete(i);
            if(result>0){
                //redirect to success page
                resp.sendRedirect("showFlight");
            }else{
                //jump to error page
                req.setAttribute("errMsg","delete failed");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            req.setAttribute("errMsg","id is not a number");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
