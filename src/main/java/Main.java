import entity.Car;
import entity.CarBodyType;
import org.hibernate.SessionFactory;
import service.CarService;
import util.HibernateUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        try {

            CarService carService = new CarService ();

            Car car1 = new Car ();
            car1.setBrand ("BMW");
            car1.setCarBodyType (CarBodyType.COMBI);
            car1.setColour ("Black");
            car1.setKm (500008);
            car1.setModel ("3");
            car1.setProductionDate (LocalDate.of (1993, 1, 31));


            Car car2 = new Car ();
            car2.setBrand ("Honda");
            car2.setCarBodyType (CarBodyType.CABRIO);
            car2.setColour ("Red");
            car2.setKm (755070);
            car2.setModel ("Civic");
            car2.setProductionDate (LocalDate.of (2015, 5, 27));

            Car car3 = new Car ();
            car3.setBrand ("Citroen");
            car3.setCarBodyType (CarBodyType.SEDAN);
            car3.setColour ("Yelow");
            car3.setKm (500);
            car3.setModel ("C3");
            car3.setProductionDate (LocalDate.of (2018, 8, 1));

            Car car4 = new Car ();
            car4.setBrand ("WV");
            car4.setCarBodyType (CarBodyType.HEATCHBACK);
            car4.setColour ("White");
            car4.setKm (55555);
            car4.setModel ("Golf");
            car4.setProductionDate (LocalDate.of (2005, 12, 1));

            Car car5 = new Car ();
            car5.setBrand ("Fiat");
            car5.setCarBodyType (CarBodyType.CABRIO);
            car5.setColour ("Red");
            car5.setKm (123000);
            car5.setModel ("126p");
            car5.setProductionDate (LocalDate.of (1973, 8, 1));


            carService.addCar (car1);
            carService.addCar (car2);
            carService.addCar (car3);
            carService.addCar (car4);
            carService.addCar (car5);

            //System.out.println (car1.getAge ());

            //carService.displayCarInfoByID (2L);
       /* System.out.println ("Can info:" );
        carService.displayCarInfoByID ( 2L);
        System.out.println ("canging...");
        carService.changeCarMilage (2L, 8887788L);
        carService.changeCarColour (2L, "Green");
        System.out.println ("Can info after change:" );
        carService.displayCarInfoByID (2L);
        carService.removeCarWithId (1L);*/

            //carService.printAllCars ();
            System.out.println ();
            //carService.sortCarsByMilage ();
            //carService.sortCarsByPriductionDate ();
            //carService.printOldesrCar ();
            //carService.printNewestCar ();
            //carService.printCarsOfModel ("Civic");
            //carService.printCarsOfBodyType (CarBodyType.CABRIO);
            carService.printCarWithBigestMmilage ();
            carService.printCarWithSmallestMmilage ();

            //zamykanie session factory i konczenie programu
        } finally {
                    /*SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        sessionFactory.close ();*/
            HibernateUtils.getSessionFactory ().close ();

        }
    }
}