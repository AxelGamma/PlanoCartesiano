import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import javax.swing.*;

public class CartesianFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {    //main
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                CartesianFrame frame = new CartesianFrame();

            }
        });
    }

    int xt = 0, yt = 0;
    CartesianPanel panel;
    JMenuBar bar = new JMenuBar();
    JMenu Trasladar, Escalar;
    JTextField x, y;
    JLabel tx, ty;
    JButton b;

    public CartesianFrame() {
        super("Plano Cartesiano");
        panel = new CartesianPanel();
        add(panel);
        Componentes();
    }

    public void Componentes() {// JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Plano Cartesiano");
        setSize(700, 700);
        setVisible(true);
        setLocationRelativeTo(null);

        // Barra de menu
        setJMenuBar(bar);
        Trasladar = new JMenu("Trasladar");
        Escalar = new JMenu("Escalar");

        bar.add(Trasladar);
        bar.add(Escalar);

        tx = new JLabel("Tx :");
        x = new JTextField(6);
        Trasladar.add(tx);
        Trasladar.add(x);

        ty = new JLabel("Ty :");
        y = new JTextField(6);
        Trasladar.add(ty);
        Trasladar.add(y);
        b = new JButton("Aceptar");
        Trasladar.add(b);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            xt = Integer.parseInt(x.getText());
            yt = -1 * Integer.parseInt(y.getText());
            repaint();
            System.out.println(xt + "" + yt);
        }
    }

    public class CartesianPanel extends JPanel { // Plano cartesiano con la figura
        public double coord_x(double x) {
            double r_x = x + this.getWidth() / 2;
            return r_x;
        }

        public double coord_y(double y) {
            double r_y = -y + this.getHeight() / 2;
            return r_y;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (int i = 0; i < this.getWidth(); i++) { //Anchura de las lineas

                Line2D linea = new Line2D.Double(i, 0, i, this.getHeight());

                if (i % 10 == 0) {
                    g.setColor(new Color(204, 204, 204));
                    g2.draw(linea);
                }

            }

            for (int i = 0; i < this.getHeight(); i++) {    //Altura de las lineas
                Line2D linea = new Line2D.Double(0, i, this.getWidth(), i);
                if (i % 10 == 0) {
                    g.setColor(new Color(204, 204, 204));
                    g2.draw(linea);
                }
            }

            //Dibujo lineas del plano
            g.setColor(Color.BLACK);
            Line2D linea_y = new Line2D.Double(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
            Line2D linea_x = new Line2D.Double(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
            g2.draw(linea_x);
            g2.draw(linea_y);


            g2.translate(xt, yt);

            Murciegalo mur = new Murciegalo();

            for (int i = 0; i < mur.x.length - 1; i++) {

                Line2D l = new Line2D.Double(coord_x(mur.x[i] * 10), coord_y(mur.y[i] * 10), coord_x(mur.x[i + 1] * 10), coord_y(mur.y[i + 1] * 10));
                g2.draw(l);
                if (xt > this.getWidth() || yt > getHeight()) {
                    xt=0;
                    yt=0;
                    JOptionPane.showMessageDialog(null,"La figura exede de la venta");
                    System.out.println("repitio");
                }
            }



        }

    }
}