package service;

import DAO.CarDAO;
import entity.Car;
import entity.CarBodyType;

import java.util.List;

public class CarService {

    private CarDAO carDAO = new CarDAO ();

    public void addCar(Car car) {
        carDAO.create (car);
    }

    public void displayCarInfoByID(Long id) {
        System.out.println (carDAO.findCarWithId (id));
    }

    public void changeCarMilage(Long id, Long km) {
        System.out.println (carDAO.changeMilage (id, km));
    }

    public void changeCarColour(Long id, String colour) {
        System.out.println (carDAO.changeColour (id, colour));
    }

    public void removeCarWithId(Long id) {
        System.out.println ("Car with id: " + id + " has been removed");
        carDAO.remove (id);
    }

    public void printAllCars() {
        System.out.println ("All available cars: ");
        List<Car> cars = carDAO.findAll ();
        cars.forEach (System.out::println);
    }

    public void sortCarsByPriductionDate() {
        System.out.println ("Cars sorted by production date: ");
        List<Car> cars = carDAO.sortByProductionDate ();
        cars.forEach (System.out::println);
    }

    public void sortCarsByMilage() {
        System.out.println ("Cars sorted by milage: ");
        List<Car> cars = carDAO.sortByMilage ();
        cars.forEach (System.out::println);
    }

    public void printOldesrCar() {
        System.out.println ("Oldest car is");
        System.out.println (carDAO.findOldest ());
    }

    public void printNewestCar() {
        System.out.println ("Newest car is");
        System.out.println (carDAO.findNewest ());
    }

    public void printCarsOfModel(String model) {
        System.out.println ("Cars of model " + model + ": ");
        List<Car> cars = carDAO.searchByModel (model);
        cars.forEach (System.out::println);
    }

    public void printCarsOfBodyType(CarBodyType type) {
        System.out.println ("Cars of body type" + type + ": ");
        List<Car> cars = carDAO.searchByBodyType (type);
        cars.forEach (System.out::println);
    }

    public void printCarWithBigestMmilage (){
        System.out.println ("Car with the bigest milage in km: ");
        System.out.println (carDAO.findBigestMilage ());
    }

    public void printCarWithSmallestMmilage (){
        System.out.println ("Car with the smalest milage in km: ");
        System.out.println (carDAO.findSmallestMilage ());
    }


}
