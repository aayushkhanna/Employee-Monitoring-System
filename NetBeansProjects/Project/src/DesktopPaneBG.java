
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.JDesktopPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author umesh
 */
public class DesktopPaneBG extends JDesktopPane {

    private ImageIcon img;
    private String backgroundImage="e1.jpg";

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public DesktopPaneBG() {
        this.img = new ImageIcon(backgroundImage);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {

        super.paintComponent(grphcs);
        grphcs.drawImage(img.getImage(), 0, 0, null);
    }

}
