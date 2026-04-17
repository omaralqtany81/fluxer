package com.fluxer.java.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class RankCardGenerator {
    public byte[] generate(String username, int level, long xp, long targetXp, String avatarUrl) {
        try {
            BufferedImage image = new BufferedImage(800, 200, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Background
            g.setColor(new Color(35, 39, 42));
            g.fillRoundRect(0, 0, 800, 200, 30, 30);

            // Progress Bar
            g.setColor(new Color(44, 47, 51));
            g.fillRoundRect(220, 130, 530, 30, 15, 15);
            
            double progress = (double) xp / targetXp;
            g.setColor(new Color(114, 137, 218));
            g.fillRoundRect(220, 130, (int) (530 * progress), 30, 15, 15);

            // Text
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString(username, 220, 70);
            
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Level " + level, 220, 110);
            g.drawString(xp + " / " + targetXp + " XP", 580, 110);

            // Avatar placeholder
            g.setColor(new Color(114, 137, 218));
            g.fillOval(30, 30, 140, 140);

            g.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
}
