package kcart.dao;

import java.util.List;
import kcart.model.Car;

public interface CarDAO {
    List<Car> getAllCars();
    boolean addCar(Car car);
    boolean editCar(Car car);
    byte[] getCarPhotoById(int carId);
    Car getCarById(int carId);
    Car getCarInfo(int carId);
}