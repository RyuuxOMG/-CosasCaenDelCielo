package autonoma.cosascaendelcielo.elements;

public class AddFoodThread implements Runnable {
    private Sky sky;
    private boolean running = true;

    public AddFoodThread(Sky sky) {
        this.sky = sky;
    }

    @Override
    public void run() {
        while (running) {
            this.sky.addSprites(1, 0);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}