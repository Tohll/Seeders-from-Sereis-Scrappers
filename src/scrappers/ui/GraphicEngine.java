package scrappers.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tools.ConfigFileReader;

public class GraphicEngine extends JPanel implements Runnable {

    private static final long serialVersionUID = -7273880090886312807L;
    private final ConfigFileReader configFileReader= new ConfigFileReader();
    private ImageIcon bush;
    private final Font defaultFont;
    private final int delay;
    private final boolean hasToDisplay;
    private final Random rand;
    //private SpriteInfos[] sceneryArray;
    private ImageIcon shadow;
    private final Font smallDefaultFont;
    private final Color uiBackgroundColor;

    public GraphicEngine() throws NumberFormatException, IOException {
        this.defaultFont = new Font("Arial", Font.BOLD, 18);
        this.smallDefaultFont = new Font("Arial", Font.BOLD, 11);
        this.uiBackgroundColor = new Color(0, 0, 0, 0.3f);
        this.delay = 25;
        this.rand = new Random();
        this.hasToDisplay = true;
        this.setBackground(new Color(0, 0, 0));
        this.setPreferredSize(new Dimension(Integer.parseInt(configFileReader.getPropertieValue("width")), Integer.parseInt(configFileReader.getPropertieValue("height"))));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        Thread animator;
        animator = new Thread(this);
        animator.start();
    }

//    private void drawLifeBar(final Graphics g, final int width, final int height, final int x, final int y,
//            final RunnableHolder creep) {
//        g.setColor(this.uiBackgroundColor);
//        g.fillRect(x, y, width, height);
//        final double div = (double) (creep._getRunnable()._getLife()) / (double) (creep._getRunnable()._getMaxLife());
//        final double lifePercentage = 100d * div;
//        if (lifePercentage > 50) {
//            g.setColor(Color.GREEN);
//        } else if (lifePercentage > 25) {
//            g.setColor(Color.ORANGE);
//        } else {
//            g.setColor(Color.RED);
//        }
//        final double aDouble = width / 100d;
//        final double bDouble = aDouble * lifePercentage;
//        final int percentWidth = (int) (this.round(bDouble, 2));
//        g.fillRect(x, y, percentWidth, height);
//        if (creep._getRunnable()._getLife() > 0) {
//            g.setColor(Color.BLACK);
//        } else {
//            g.setColor(Color.RED);
//        }
//        g.drawRect(x, y, width, height);
//    }


//    private void drawScenery(final Graphics g) {
//        final Graphics2D g2d = (Graphics2D) g;
//        final AffineTransform backup = g2d.getTransform();
//        for (final SpriteInfos spriteInfos : this.sceneryArray) {
//            g2d.setTransform(AffineTransform.getRotateInstance(spriteInfos._getRotate(), spriteInfos._getPosition().x,
//                    spriteInfos._getPosition().y));
//            g2d.drawImage(this.bush.getImage(), spriteInfos._getPosition().x - (spriteInfos._getWidth() / 2),
//                    spriteInfos._getPosition().y - (spriteInfos._getWidth() / 2),
//                    spriteInfos._getWidth() < 1 ? 2 : spriteInfos._getWidth(),
//                    spriteInfos._getWidth() < 1 ? 2 : spriteInfos._getWidth(), this);
//        }
//        g2d.setTransform(backup);
//    }

    private void drawShadows(final Graphics g) {
//        g.drawImage(this.shadow.getImage(), 0, 0, Configuration.WIDTH, Configuration.HEIGHT, this);
    }


    private void drawUI(final Graphics g) {
        g.setColor(this.uiBackgroundColor);
        g.fillRect(15, 20, 122, 50);
        g.setFont(this.defaultFont);
        g.setColor(Color.WHITE);
        g.drawString(String.format("Width : %d", this.getWidth()), 20, 40);
        g.drawString(String.format("Height : %d", this.getHeight()), 20, 62);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.drawUI(g);
    }

    private double round(final double value, final int precision) {
        final int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public void run() {
        long beforeTime;
        long timeDiff;
        long sleep;
        beforeTime = System.currentTimeMillis();
        while (this.hasToDisplay) {
            this.repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = this.delay - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (final InterruptedException e) {
                final String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
                Thread.currentThread().interrupt();
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
