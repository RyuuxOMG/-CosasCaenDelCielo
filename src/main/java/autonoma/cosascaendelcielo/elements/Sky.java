package autonoma.cosascaendelcielo.elements;


import autonoma.cosascaendelcielo.elements.SpriteContainer;
import autonoma.cosascaendelcielo.elements.Sprite;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import autonoma.cosascaendelcielo.elements.Food;
import autonoma.cosascaendelcielo.elements.Poison;

public class Sky extends SpriteContainer {
    private int score;
    private int foodCount;

    public Sky(String path, int x, int y, int height, int width) {
        super(path, x, y, height, width);
        this.score = 0;
        this.foodCount = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public void addSprite(Sprite sprite) {
        this.add(sprite);
        if (sprite instanceof Food) {
            foodCount++;
        }
    }

    public void removeSprite(Sprite sprite) {
        this.remove(sprite);
        if (sprite instanceof Food) {
            foodCount--;
        }
    }

    public void moveSprites() {
        Iterator<Sprite> iterator = this.sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = iterator.next();
            if (sprite instanceof MovableSprite) {
                MovableSprite movable = (MovableSprite) sprite;
                movable.setY(movable.getY() + movable.getStep());

                if (movable.isOutOfGraphicContainer()) {
                    iterator.remove();
                    if (movable instanceof Food) {
                        foodCount--;
                    }
                }
            }
        }
    }

    public void catchSprite(int x, int y) throws IOException {
        ArrayList<Sprite> caughtSprites = new ArrayList<>();

        for (Sprite sprite : this.getSprites()) {
            if (x >= sprite.getX() && x <= sprite.getX() + sprite.getWidth() &&
                y >= sprite.getY() && y <= sprite.getY() + sprite.getHeight()) {

                if (sprite instanceof Food) {
                    score += ((Food) sprite).getPoints();
                } else if (sprite instanceof Poison) {
                    score -= ((Poison) sprite).getPenalty();
                }

                caughtSprites.add(sprite);
            }
        }

        for (Sprite sprite : caughtSprites) {
            this.removeSprite(sprite);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Sprite sprite : this.sprites) {
            sprite.paint(g);
        }
    }
}
