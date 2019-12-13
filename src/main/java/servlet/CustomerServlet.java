package servlet;

import com.google.gson.Gson;
import model.Car;
import model.DailyReport;
import service.CarService;
import service.DailyReportService;

import javax.persistence.Column;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.getWriter().write(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");

        if (brand != null & model != null & licensePlate != null) {
            DailyReportService dailyReportService = DailyReportService.getInstance();
            CarService carService = CarService.getInstance();
            Car car = carService.getCar(brand, model, licensePlate);
            CarService.getInstance().deleteCar(car);

            dailyReportService.setEarnings(car.getPrice());
        }
    }
}
