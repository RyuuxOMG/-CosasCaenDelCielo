package autonoma.cosascaendelcielo.elements;

import autonoma.cosascaendelcielo.elements.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Food extends Sprite {
    public static final int INITIAL_WIDTH = 50;
    public static final int INITIAL_HEIGHT = 50;
    private BufferedImage image;
    protected int step = 5;
    private int velocityY = 0;
    private int velocityX;
    private int gravity = 1;
    private boolean moving = false;
    private Graphics bufferGraphics;

    public Food(String path, int x, int y, int height, int width) {
        super(path, x, y, height, width);
        try {
            this.image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferGraphics = image.getGraphics();
    }

    public void startMovement() {
        if (!moving) {
            velocityY = 2;
            velocityX = (int)(Math.random() * 3 - 1);
            moving = true;
        }
    }

    public void move() {
        if (moving) {
            velocityY += gravity;
            y += velocityY;
            x += velocityX;

            Rectangle boundaries = gameContainer.getBoundaries();

            if (y + height >= boundaries.height) {
                y = boundaries.height - height;
                velocityY = 0;
                moving = false;
            }

            if (x < 0) {
                x = 0;
                velocityX = -velocityX;
            }

            if (x + width > boundaries.width) {
                x = boundaries.width - width;
                velocityX = -velocityX;
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
        g.drawImage(image, 0, 0, this);
    }
}
