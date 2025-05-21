package autonoma.cosascaendelcielo.elements;

import autonoma.cosascaendelcielo.elements.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Poison extends Sprite {

    public static final int INITIAL_WIDTH = 50;
    public static final int INITIAL_HEIGHT = 50;

    private BufferedImage image;
    protected int step = 5;
    private int speedY = 0;
    private int speedX;
    private int gravity = 1;
    private boolean moving = false;
    private Graphics bufferGraphics;

    public Poison(String path, int x, int y, int height, int width) {
        super(path, x, y, height, width);
        try {
            this.image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferGraphics = image.getGraphics();
    }

    public void startMoving() {
        if (!moving) {
            speedY = 2;
            speedX = (int) (Math.random() * 3 - 1);
            moving = true;
        }
    }

    public void move() {
        if (moving) {
            speedY += gravity;
            y += speedY;
            x += speedX;

            Rectangle bounds = gameContainer.getBoundaries();

            if (y + height >= bounds.height) {
                y = bounds.height - height;
                speedY = -speedY;
                moving = false;
            }

            if (x < 0) {
                x = 0;
                speedX = -speedX;
            }

            if (x + width > bounds.width) {
                x = bounds.width - width;
                speedX = -speedX;
            }

            if (gameContainer != null) {
                gameContainer.refresh();
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(color != null ? color : Color.BLACK);
            g.fillRect(x, y, width, height);
        }
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}