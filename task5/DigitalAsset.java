package task5;

public abstract class DigitalAsset {
    protected final StorageDriver driver;
    protected final String assetPath;

    public DigitalAsset(StorageDriver driver, String assetPath) {
        this.driver = driver;
        this.assetPath = assetPath;
    }

    public abstract void render();
}
