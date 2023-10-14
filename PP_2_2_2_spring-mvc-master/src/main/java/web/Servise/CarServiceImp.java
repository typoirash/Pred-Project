package web.Servise;

import org.springframework.stereotype.Service;
import web.Dao.CarDao;
import web.Model.Car;

import java.util.List;

@Service
public class CarServiceImp implements CarServise {
    private CarDao carDao;

    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    /*@Override
    public List<Car> cars() {
        return carDao.cars();
    }
*/
    @Override
    public List<Car> returnCar(int quantity) {
        return carDao.returnCar(quantity);
    }
}
