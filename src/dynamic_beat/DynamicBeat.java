package dynamic_beat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class DynamicBeat extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;

    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEnter.png"));
    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

    private Image introBackground = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();;
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));
    private JButton exitButton = new JButton(exitButtonBasicImage);


    private int mouseX, mouseY;

    public DynamicBeat(){
        setTitle("Dynamic Beat");
        setUndecorated(true);
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        // 닫기버튼 초기화
        exitButton.setBounds(1245, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitButton.setIcon(exitButtonBasicImage);
            }
            @Override
            public void mousePressed(MouseEvent e){
                System.exit(0);
            }

        });
        add(exitButton);

        // 메뉴바 초기화
        menuBar.setBounds(0, 0, 1280, 30);
        // 마우스 좌표 계산
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        // 메뉴바를 잡고 윈도우 전체를 이동하기 위한 이벤트
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        add(menuBar);


        // 이미지 크기를 스크린에 맞게 변환
        introBackground = introBackground.getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, Image.SCALE_SMOOTH);

        Music introMusic = new Music("introMusic.mp3", true);
        introMusic.start();
    }

    // 이미지 배경을 매순간마다 그려줌
    @Override
    public void paint(Graphics graphic){
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // screenImage의 Graphics 객체를 생성
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);
        graphic.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics graphic){
        graphic.drawImage(introBackground, 0, 0, null);
        paintComponents(graphic);
        this.repaint();

    }
}
