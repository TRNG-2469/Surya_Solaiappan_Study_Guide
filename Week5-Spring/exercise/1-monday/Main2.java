import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfiguration.xml");

        Car car = (Car) context.getBean("CarBean");

        System.out.println("Car Model: " + car.getModel());
        System.out.println("Price: " + car.getPrice());
        System.out.println("Engine: " + car.getEngine().getType());
        System.out.println("Horse Power: " + car.getEngine().getHorsePower());
    }
}