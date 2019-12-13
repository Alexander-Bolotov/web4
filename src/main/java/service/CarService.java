package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        List<Car> listCars = new CarDao(sessionFactory.openSession()).getAllCars();
        return listCars;
    }

    public Car getCar(String brand, String model, String licensePlate) {
        Car car = new CarDao(sessionFactory.openSession()).getCar(brand, model, licensePlate);
        return car;
    }

    public void deleteCar(Car car) {
        new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public void addCar(Car car) {
        new CarDao(sessionFactory.openSession()).addCar(car);
    }

    public Boolean getPermissionToAdd(String brand) {
        if (new CarDao(sessionFactory.openSession()).getCarCountByBrand(brand) <= 10) {
            return true;
        } else {
            return false;
        }

    }

    public void deleteCars() {
        new CarDao(sessionFactory.openSession()).deleteCars();
    }
}