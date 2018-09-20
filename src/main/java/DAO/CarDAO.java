package DAO;

import entity.Car;
import entity.CarBodyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public void create(Car car) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        try (Session session = sessionFactory.openSession ()) {
            Transaction transaction = session.beginTransaction ();
            session.persist (car);
            transaction.commit ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public Car findCarWithId(Long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();  //
        Session session = sessionFactory.openSession ();
        Car car = new Car ();
        //String query = "select c from Car c where id = :id";
        try {
            car = session.createNamedQuery ("Car.findById", Car.class)
                    .setParameter ("id", id)
                    .getSingleResult ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }
        return car;
    }

    public Car changeMilage(Long id, Long km) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        Transaction transaction = session.beginTransaction ();
        Car car = findCarWithId (id);
        try {
            session.update (car);
            Car carPersisted = session.find (Car.class, car.getId ());
            carPersisted.setKm (km);
            transaction.commit ();
        } catch (Exception e) {
            transaction.rollback ();
            session.close ();
        }
        return car;
    }

    public Car changeColour(Long id, String colour) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        Transaction transaction = session.beginTransaction ();
        Car car = findCarWithId (id);
        try {
            session.update (car);
            Car carPersisted = session.find (Car.class, car.getId ());
            carPersisted.setColour (colour);
            transaction.commit ();
        } catch (Exception e) {
            transaction.rollback ();
            session.close ();
        }
        return car;
    }

    public void remove(Long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        Transaction transaction = session.beginTransaction ();
        Car car = findCarWithId (id);
        try {
            session.remove (car);
            transaction.commit ();
        } catch (Exception e) {
            transaction.rollback ();
        } finally {
            session.close ();
        }
    }

    public List<Car> findAll() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();  // zawsze jedna na aplikacje, na podstawie plicu cfg
        Session session = sessionFactory.openSession (); // odpowiednik entity managera
        List<Car> cars = new ArrayList<Car> ();
        try {
            String query = "select c from Car c";
            cars = session
                    .createQuery (query, Car.class)
                    .getResultList ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }
        return cars;
    }

    public List<Car> sortByProductionDate() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        List<Car> cars = new ArrayList<> ();
        try {
            String query = "select c from Car c order by productionDate";
            cars = session
                    .createQuery (query, Car.class)
                    .getResultList ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }
        return cars;
    }

    public List<Car> sortByMilage() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        List<Car> cars = new ArrayList<> ();
        try {
            String query = "select c from Car c order by km";
            cars = session
                    .createQuery (query, Car.class)
                    .getResultList ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }
        return cars;
    }

    public Car findOldest() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Car car = new Car ();
        try (Session session = sessionFactory.openSession ()) {
            String query = "select c from Car c order by productionDate";
            car = session
                    .createQuery (query, Car.class)
                    .setMaxResults (1)
                    .getSingleResult ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return car;
    }

    public Car findNewest() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        Car car = new Car ();
        try {
            String query = "select c from Car c order by productionDate desc";
            car = session
                    .createQuery (query, Car.class)
                    .setMaxResults (1)
                    .getSingleResult ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }
        return car;
    }

    public List<Car> searchByModel(String model) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        List<Car> cars = new ArrayList<> ();
        try (Session session = sessionFactory.openSession ()) {
            String query = "select c from Car c where c.model like:model";
            cars = session
                    .createQuery (query, Car.class)
                    .setParameter ("model", model)
                    .getResultList ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return cars;
    }

    public List<Car> searchByBodyType(CarBodyType carBodyType) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory ();
        Session session = sessionFactory.openSession ();
        List<Car> cars = new ArrayList<> ();
        try {
            String query = "select c from Car c where c.carBodyType like:carBodyType";
            cars = session
                    .createQuery (query, Car.class)
                    .setParameter ("carBodyType", carBodyType)
                    .getResultList ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }
        return cars;
    }

    public Car findBigestMilage() {
        SessionFactory sf = HibernateUtils.getSessionFactory ();
        Car car = new Car ();
        try (Session session = sf.openSession ()) {
            String query = "select c from Car c order by c.km desc";
            car = session
                    .createQuery (query, Car.class)
                    .setMaxResults (1)
                    .getSingleResult ();
        } catch (Exception e) {
            e.printStackTrace ();
        }return car;
    }

    public Car findSmallestMilage() {
        SessionFactory sf = HibernateUtils.getSessionFactory ();
        Car car = new Car ();
        try (Session session = sf.openSession ()) {
            String query = "select c from Car c order by c.km ";
            car = session
                    .createQuery (query, Car.class)
                    .setMaxResults (1)
                    .getSingleResult ();
        } catch (Exception e) {
            e.printStackTrace ();
        }return car;
    }
}




/* min max year : findAllByProductionDateOrderedBY
setMaxResult(1)


flush - wymuszenie zapisu sesji do bazy przed commitem

session/entity menager -> JSR JPA
hibernate rozszerza JPA

 */