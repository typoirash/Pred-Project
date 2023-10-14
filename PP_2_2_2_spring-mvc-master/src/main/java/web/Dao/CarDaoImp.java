package web.Dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.Model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarDaoImp implements CarDao {
    private List<Car> carList;

    {
        carList = new ArrayList<>();
        Car car1 = new Car("BMW", "M", 5);
        Car car2 = new Car("BMW", "i", 3);
        Car car3 = new Car("Mersedes", "AMG", 540);
        Car car4 = new Car("Zil", "T", 50);
        Car car5 = new Car("Kamaz", "Master", 1);

        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);
        carList.add(car5);
    }

    /*@Override
    public List<Car> cars() {
        return carList;
    }*/

    @Override
    public List<Car> returnCar (int quantity) {
        return carList.stream().limit(quantity).collect(Collectors.toList());
    }
}
