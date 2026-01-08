package pikachu;

import javax.swing.*;
import java.awt.*;

public class GameCharacterC {
    int x, y, width, height;
    Image image;

    public GameCharacterC(int x, int y, ImageIcon icon) {
        this.x = x;
        this.y = y;
        this.image = icon.getImage();
        this.width = icon.getIconWidth();
        this.height = icon.getIconHeight();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); 
    }
}
