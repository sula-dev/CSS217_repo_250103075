interface Pizza {
    String getDescription();
    double getCost();
}

class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Dough & Sauce";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

abstract class ToppingDecorator implements Pizza {
    protected final Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

class Cheese extends ToppingDecorator {
    public Cheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 2.0;
    }
}

class Olives extends ToppingDecorator {
    public Olives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.0;
    }
}

class Pineapple extends ToppingDecorator {
    public Pineapple(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pineapple";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.5;
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        pizza = new Cheese(pizza);
        pizza = new Olives(pizza);
        pizza = new Pineapple(pizza);

        Pizza pizza2=new PlainPizza();
        pizza2=new Cheese(new Olives(new Pineapple(new Cheese(pizza2))));

        System.out.println("Description: " + pizza.getDescription());
        System.out.printf("Total cost: $%.2f%n", pizza.getCost());

        System.out.println();

        System.out.println("Description: "+pizza2.getDescription());
        System.out.printf("Total cost: $%.2f%n", pizza2.getCost());
    }
}