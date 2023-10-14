package web.Dao;

import web.Model.Car;

import java.util.List;

public interface CarDao {
    /*List<Car> cars();*/
    List<Car> returnCar (int quantity);
}
