/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This is code is the original work of Timon Davis.  Please feel free
 * to copy, use and redistribute.  Please include this comment
 * block, grouped with the code you are re-using, when doing so.
 *
 * Feel free to modify as you wish, but please add an additional comment
 * noting the modifications that have been made.
 * @author Timon Davis
 */
public class DynamicPanelTutorial {

    /**
     * This is the class representing our dynamic panel
     */
    public static class DynamicPanel extends JPanel {

        static int width;
        static int height;

        /**
         * CONSTRUCTOR
         */
        public DynamicPanel() {
            width = 5;
            height = 5;

            // CRITICAL:  the JScrollPane uses the getPreferredSize() method to
            // determine how big to make itself.  Failing to set preferred size
            // will mean that your viewing window will not change size to
            // accommodate your new JPanel size.
            setPreferredSize(new Dimension(width, height));
        }

        /**
         * Set the width and height of the checkerboard
         *
         * @param w
         *   The width of the new checkerboard
         * @param h
         *   The height of the new checkerboard
         */
        public void setDimensions(int w, int h) {

            width = w;
            height = h;

            // CRITICAL:  the JScrollPane uses the getPreferredSize() method to
            // determine how big to make itself.  Failing to set preferred size
            // will mean that your viewing window will not change size to
            // accommodate your new JPanel size.
            setPreferredSize(new Dimension(width * (32 + 1), height * (32 + 1)));
        }

        /**
         * This method is used to paint images onto the panel.
         *
         * It overrides the paintComponent method belonging to JPanel
         *
         * @param g
         *   This is the canvas on which you will paint
         */
        protected void paintComponent(Graphics g) {
            // Prevents images from getting gunky / forces full redraw
            super.paintComponent(g);

            // Initialize brush
            g.setColor(Color.red);

            // Draw a checkerboard on the screen based on height / weight value
            for (int x = 0 ; x < width ; x++) {
                for (int y = 0 ; y < height ; y++) {

                    if ( (0 == x % 2) && 0 == (y % 2) ||
                            (x % 2 > 0) && (y % 2 > 0  )) {
                        // draw a 32 x 32 rectangle
                        g.drawRect(x * 32 + 1, y * 32 + 1, 32, 32);
                    }
                    else
                    {
                        g.fillRect(x * 32 + 1, y * 32 + 1, 32, 32);
                    }
                }
            }
        }
    }

    /**
     * This is the panel which will contain our dynamically sized panel
     * in our scrollbar.
     */
    public static class DynamicPanelDisplay extends JFrame {

        JScrollPane pane = new JScrollPane();
        DynamicPanel dynamicPanel = new DynamicPanel();
        JPanel inputPanel = new JPanel();

        // Fields we'll use to set our width and height.
        JTextField txt_width = new JTextField("5");
        JLabel lbl_width = new JLabel("Width");

        JTextField txt_height = new JTextField("5");
        JLabel lbl_height = new JLabel("Height");

        JButton btn_update = new JButton("Update");

        /**
         * CONSTRUCTOR
         */
        public DynamicPanelDisplay() {
            // Initiate Scroll Pane, plugging in the Dynamic Panel we created
            // above.
            pane = new JScrollPane(dynamicPanel);

            // Set the frame layout and add our scroll pane.
            setLayout(new GridLayout(1,2));
            add(pane);



            // CRITICAL CODE.  This is where we call for a size update on
            // our checkerboard, and then make the adjustments to display a
            // resized JPanel correctly.
            btn_update.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    // Grab our values from the user, then parse into ints.
                    String str_width = txt_width.getText();
                    String str_height = txt_height.getText();

                    int width = Integer.parseInt(str_width);
                    int height = Integer.parseInt(str_height);

                    // Reset dimensions of the checkerboard.
                    dynamicPanel.setDimensions(width, height);

                    // CRITICAL:  call update UI to refresh the look and feel
                    // of the pane.  Failing to do this will yield unpredictable
                    // visual results.
                    pane.updateUI();
                }

            });

            // The remaining code is not critical to the exercise at hand.  Laying out the
            // contents here.
            inputPanel.setLayout(new GridLayout(3,2));
            inputPanel.add(lbl_width);
            inputPanel.add(txt_width);
            inputPanel.add(lbl_height);
            inputPanel.add(txt_height);
            inputPanel.add(btn_update);

            // add the input panel to the frame
            add(inputPanel);

            // Set up our main frame display
            setSize(600,400);
            setVisible(true);
            setTitle("Dynamic Panel Display Tutorial");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

        }
    }

    public static void main(String args[]) {

        // Create the new frame for display
        DynamicPanelDisplay frame = new DynamicPanelDisplay();
    }
}
