package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Servise.CarServiceImp;

@Controller
@RequestMapping("/")
public class CarsController {
    private CarServiceImp carServiceImp;

    public CarsController(CarServiceImp carServiceImp) {
        this.carServiceImp = carServiceImp;
    }

    @GetMapping("/cars")
    public String countList(@RequestParam(value = "count",defaultValue = "5") Integer count, Model model) {  //, required = false
        model.addAttribute("cars", carServiceImp.returnCar(count));
        return "cars";
    }
    /*@GetMapping("/cars")
    public String printCars(Model model) {
        model.addAttribute("cars", carServiceImp.cars());
        return "cars";
    }*/
    /*@RequestMapping("/car-list")
    public String carList(Model model) {
        Car car1 = new Car("BMW", "M", 5);
        Car car2 = new Car("BMW", "i", 3);
        Car car3 = new Car("Mersedes", "AMG", 540);
        Car car4 = new Car("Zil", "T", 50);
        Car car5 = new Car("Kamaz", "Master", 1);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);
        carList.add(car5);
        model.addAttribute("car", carList);
        return "car-list";
    }*/

    /*@GetMapping(value = "/cars")
    public String printCars(ModelMap car) {
        List<String> cars = new ArrayList<>();
        cars.add("Brand");
        cars.add("Model");
        cars.add("Series");
        car.addAttribute("cars", cars);
        return "cars";
    }*/

}
