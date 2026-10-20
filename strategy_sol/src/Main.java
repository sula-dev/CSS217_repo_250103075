interface MovementStrategy {
    void move();
}

class Walking implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Walking at 5 km/h");
    }
}

class Skateboarding implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Skateboarding at 15 km/h");
    }
}

class Jetpack implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Flying with a jetpack at 60 km/h");
    }
}

class Player {
    private MovementStrategy strategy;

    public Player(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void move() {
        strategy.move();
    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player(new Walking());

        System.out.println("--- Start of the run ---");
        player.move();
        player.move();

        System.out.println("--- Found a skateboard! ---");
        player.setStrategy(new Skateboarding());
        player.move();

        System.out.println("--- Grabbed a jetpack! ---");
        player.setStrategy(new Jetpack());
        player.move();
        player.move();
    }
}