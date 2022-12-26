package dynamic_beat;

import javax.swing.*;
import java.awt.*;

public class DynamicBeat extends JFrame {

    // 배경 관련 필드
    private Image screenImage;
    private Graphics screenGraphic;
    private Image introBackground;
    private Image changeImg;

    public DynamicBeat(){
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        introBackground = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
        // 이미지 크기를 스크린에 맞게 변환
        introBackground = introBackground.getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, Image.SCALE_SMOOTH);

        Music introMusic = new Music("introMusic.mp3", true);
        introMusic.start();
    }

    // ImageIcon을 image로 변환하고 size를 조절한 뒤 다시 ImageIcon으로 변환
    // 코드가 더 늘어나면 따로 클래스로 빼겠음..??
//    public Image changeImageSize(Image icon, int width, int height){
//
//    }

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
        this.repaint();

    }
}
