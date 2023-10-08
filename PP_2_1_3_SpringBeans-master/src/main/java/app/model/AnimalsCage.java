package app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AnimalsCage {


    /*@Autowired@Qualifier("cat")
    private Animal cat;
    @Autowired@Qualifier("dog")
    private Animal dog;*/

    //@Autowired
    /*private Animal animalCat;
    private Animal animalDog;*/

    @Autowired @Qualifier("dog")
    private Animal animal;

    @Autowired
    private Timer timer;

    /*@Autowired
    public AnimalsCage(@Qualifier("cat") Animal animalCat, @Qualifier("cat") Animal animalDog) {
        this.animalCat = animalCat;
        this.animalDog = animalDog;
    }*/

    public void whatAnimalSay() {
        System.out.println("Say:");
        System.out.println(animal.toString());
        System.out.println("At:");
        System.out.println(new Timer().getTime());
        System.out.println(timer.getTime());
        System.out.println("________________________");
    }


}
