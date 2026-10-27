public class LabSolution {
    public static void main(String[] args) {
        System.out.println("=== TASK 1: The Bouncer ===");
        Image guestProxy = new ProxyImage("salary_sheet.png", "GUEST");
        Image adminProxy = new ProxyImage("salary_sheet.png", "ADMIN");
        guestProxy.show();
        adminProxy.show();

        System.out.println("\n=== TASK 2: The View Counter ===");
        Video video = new ProxyVideo("Cat_Memes.mp4");
        video.play();
        video.play();
        video.play();

        System.out.println("\n=== TASK 3: School Wi-Fi Firewall ===");
        Internet internet = new SchoolProxyInternet();
        internet.connectTo("wikipedia.org");
        internet.connectTo("gaming.com");

        System.out.println("\n=== TASK 4: The New Machine (Smart Light) ===");
        Device light = new SmartLight();
        AdvancedRemote remote = new AdvancedRemote(light);
        remote.powerOn();
        remote.mute();

        System.out.println("\n=== TASK 5: The New Button (Party Remote) ===");
        Device radio = new Radio();
        PartyRemote partyRemote = new PartyRemote(radio);
        partyRemote.partyMode();

        System.out.println("\n=== TASK 6: RPG Game Weapon System ===");
        Hero knightWithSword = new Knight(new Sword());
        knightWithSword.attack();

        Hero knightWithWand = new Knight(new MagicWand());
        knightWithWand.attack();
    }
}

// ==========================================
// PART 1: THE PROXY PATTERN CLASSES
// ==========================================

// Task 1 Classes
interface Image {
    void show();
}

class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }

    @Override
    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}

class ProxyImage implements Image {
    private String fileName;
    private String userRole;
    private RealImage realImage;

    public ProxyImage(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }

    @Override
    public void show() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println("[ACCESS DENIED] You lack permission to view: " + fileName);
            return;
        }
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}

// Task 2 Classes
interface Video {
    void play();
}

class RealVideo implements Video {
    private String fileName;

    public RealVideo(String fileName) {
        this.fileName = fileName;
        System.out.println("--> [DOWNLOADING] Connecting to server & buffering 4K video: " + fileName);
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + fileName);
    }
}

class ProxyVideo implements Video {
    private String fileName;
    private RealVideo realVideo;
    private int viewCount = 0;

    public ProxyVideo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void play() {
        viewCount++;
        System.out.println("--> [AUDIT] Video has been played " + viewCount + " time(s).");
        if (realVideo == null) {
            realVideo = new RealVideo(fileName);
        }
        realVideo.play();
    }
}

// Task 3 Classes
interface Internet {
    void connectTo(String site);
}

class RealInternet implements Internet {
    @Override
    public void connectTo(String site) {
        System.out.println("Connected to site: " + site);
    }
}

class SchoolProxyInternet implements Internet {
    private RealInternet realInternet;

    @Override
    public void connectTo(String site) {
        if ("gaming.com".equals(site) || "socialmedia.com".equals(site)) {
            System.out.println("[BLOCKED BY FIREWALL] Cannot access: " + site);
            return;
        }
        if (realInternet == null) {
            realInternet = new RealInternet();
        }
        realInternet.connectTo(site);
    }
}


// ==========================================
// PART 2: THE BRIDGE PATTERN CLASSES
// ==========================================

// Task 4 & 5 Common Device Interface & Remotes
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int level);
}

class AdvancedRemote {
    protected Device device;
    public AdvancedRemote(Device device) { this.device = device; }
    public void powerOn() { device.turnOn(); }
    public void mute() { device.setVolume(0); System.out.println("[Remote] Mute button pressed!"); }
}

class BasicRemote {
    protected Device device;
    public BasicRemote(Device device) { this.device = device; }
}

// Task 4 Classes
class SmartLight implements Device {
    @Override
    public void turnOn() { System.out.println("Smart Light glows bright white."); }
    @Override
    public void turnOff() { System.out.println("Smart Light shuts off."); }
    @Override
    public void setVolume(int level) { System.out.println("Smart Light brightness set to: " + level + "%"); }
}

// Task 5 Classes
class Radio implements Device {
    @Override
    public void turnOn() { System.out.println("Radio starts playing static/music."); }
    @Override
    public void turnOff() { System.out.println("Radio turns off."); }
    @Override
    public void setVolume(int level) { System.out.println("Radio volume knob turned to: " + level); }
}

class PartyRemote extends BasicRemote {
    public PartyRemote(Device device) {
        super(device);
    }

    public void partyMode() {
        System.out.println("[Party Remote] Pumping up the party!");
        device.turnOn();
        device.setVolume(100);
    }
}

// Task 6 Classes
interface Weapon {
    void use();
}

class Sword implements Weapon {
    @Override
    public void use() {
        System.out.println("Slash! Dealing 20 physical damage.");
    }
}

class MagicWand implements Weapon {
    @Override
    public void use() {
        System.out.println("Zap! Dealing 35 magic damage.");
    }
}

abstract class Hero {
    protected Weapon weapon;

    public Hero(Weapon weapon) {
        this.weapon = weapon;
    }

    public abstract void attack();
}

class Knight extends Hero {
    public Knight(Weapon weapon) { super(weapon); }
    @Override
    public void attack() { weapon.use(); }
}

class Wizard extends Hero {
    public Wizard(Weapon weapon) { super(weapon); }
    @Override
    public void attack() { weapon.use(); }
}