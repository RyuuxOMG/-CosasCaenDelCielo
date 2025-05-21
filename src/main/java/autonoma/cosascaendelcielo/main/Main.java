package autonoma.cosascaendelcielo.main;

import autonoma.cosascaendelcielo.elements.Sky;
import autonoma.cosascaendelcielo.elements.AddFoodThread;
import autonoma.cosascaendelcielo.elements.AddPoisonThread;
import autonoma.cosascaendelcielo.elements.MoveSpritesThread;
import autonoma.cosascaendelcielo.views.GameWindow;

public class Main {
    public static void main(String[] args) {
        Sky sky = new Sky("/autonoma/lluviahamburguesas/images/Cielo.png", 0, 0, 480, 480);

        Thread thread1 = new Thread(new AddFoodThread(sky));
        Thread thread2 = new Thread(new AddPoisonThread(sky));
        Thread thread3 = new Thread(new MoveSpritesThread(sky));

        GameWindow window = new GameWindow(sky);
        window.setSky(sky);
        sky.setGraphicContainer(window);
        window.setSize(480, 480);
        window.setTitle("   CATCH THE FOOD, WATCH OUT FOR THE POISON");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}