import sweeper.Box1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;


public class Saper  extends JFrame
{
    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IM_SIZE = 50;

//    public static void main(String[] args)
//    {
//        Saper saper = new Saper();
//        saper.setVisible(true);
//    }

    public Saper() /////формирование окнa/////
    {
        game = new Game (COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLable();
        initPanel();
        initFrame();
    }

    private void initLable ()
    {
        label= new JLabel("Welcome");
        add (label, BorderLayout.SOUTH);

    }

    private void initPanel()  /////инициализвция панели/////
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent (Graphics g)
            {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())
                {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IM_SIZE, coord.y * IM_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                        public void mousePressed(MouseEvent e)
                                        {
                                            int x = e.getX() / IM_SIZE;
                                            int y = e.getY() / IM_SIZE;
                                            Coord coord = new Coord(x, y);
                                            if (e.getButton() == MouseEvent.BUTTON1)
                                                game.pressLeftButton (coord);
                                            if (e.getButton() == MouseEvent.BUTTON2)
                                                game.start();
                                            if (e.getButton() == MouseEvent.BUTTON3)
                                                game.pressRightButton (coord);
                                            label.setText (getMessage ());
                                            panel.repaint();
                                        }
                                    });

        panel.setPreferredSize(new Dimension(
                        Ranges.getSize().x * IM_SIZE,
                        Ranges.getSize().y * IM_SIZE));
        add (panel);
    }

    private String getMessage ()
    {
        switch (game.getState())
        {
            case PLAYED : return "....";
            case BOMBED: return "ops";
            case WINNER: return "you won!!!";
            default: return "";
        }
    }

    private void initFrame() //параметры панели
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сапер");
        setResizable(false);
        setVisible(true);
        pack ();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages () //установка всех картинок
    {
        for (Box1 box: Box1.values())
            box.image = getImage(box.name().toLowerCase());
    }

   private Image getImage (String name)   //img
    {
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}