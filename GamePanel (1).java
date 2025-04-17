package com.mycompany.gamepanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Mapa_Grid mapa;
    private MeuAgente agente;

    // Construtor inicializando o mapa e o agente
    public GamePanel() {
        this.mapa = new Mapa_Grid(20, 20); // Inicializa o mapa com dimensões 20x20
        this.agente = new MeuAgente();

        // Adiciona um ouvinte de mouse para movimentar o agente ao clicar
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Calcula o caminho no mapa com base na posição do clique
                mapa.calcularCaminho(e.getX(), e.getY());
                // Atualiza a posição do agente com base no novo caminho
                agente.atualizarPosicao(mapa.getPosicaoDestino());
                // Solicita a repintura do painel para refletir as mudanças
                repaint();
            }
        });
    }

    // Método para desenhar o mapa e o agente
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha o mapa
        mapa.DesenhaSe(g2d);

        // Desenha o agente na posição atualizada
        agente.DesenhaSe(g2d);
    }

    // Método para carregar o mapa a partir de uma imagem
    public void carregarMapa() {
        try {
            mapa.loadMapFromImage("caminho/para/imagem.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Métodos getter para acessar as dimensões do mapa
    public int getAltura() {
        return mapa.getAltura();
    }

    public int getLargura() {
        return mapa.getLargura();
    }

    // Adicionando o método startGame()
    public void startGame() {
        // Lógica para iniciar o jogo
        System.out.println("Jogo iniciado!");
    }
}
