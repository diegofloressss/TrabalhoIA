package com.mycompany.gamepanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private GamePanel gp;
    public static Main instance;

    public static void main(String[] args) {
        // Garantir execução na thread de interface gráfica
        SwingUtilities.invokeLater(() -> {
            Main m = new Main();
            m.init();
        });
    }

    public void init() {
        instance = this;

        setFocusable(true);
        setTitle("A* Pathfinder");

        // Define layout do JFrame
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Instancia e adiciona o painel do jogo
        gp = new GamePanel();
        c.add(gp, BorderLayout.CENTER);

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(805, 505); // Define tamanho da janela
        setResizable(false);
        setLocationRelativeTo(null); // Centraliza na tela
        setVisible(true);

        // Inicia o loop do jogo
        gp.startGame();
    }
}
