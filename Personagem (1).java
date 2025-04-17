import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Personagem extends Agente {

    BufferedImage AnimeSet;  // Conjunto de imagens (sprites) para a animação do personagem

    int frame;               // Quadro atual da animação
    int timeranimacao;       // Controla o tempo de animação (para troca de frames)
    int animacao;            // Indica qual animação está sendo exibida
    int tempoentreframes;    // Tempo entre a troca de frames da animação
    
    int velocidade = 0;      // Velocidade do personagem
    double velx, vely;       // Velocidade nas direções X e Y (alterado para double)
    
    double ang = 0;          // Ângulo de rotação do personagem
    
    boolean segueobjetivo = false;  // Flag para saber se o personagem está seguindo um objetivo
    double objetivoX = 0;   // Posição X do objetivo
    double objetivoY = 0;   // Posição Y do objetivo
    
    int sizeX = 24;         // Largura do personagem
    int sizeY = 32;         // Altura do personagem

    // Construtor da classe
    public Personagem(BufferedImage _AnimeSet, int x, int y, int velocidade) {
        super(x, y);  // Chama o construtor da classe Agente para inicializar a posição
        AnimeSet = _AnimeSet;
        frame = 0;
        animacao = 0;
        timeranimacao = 0;
        this.velocidade = velocidade;
        velx = 0;
        vely = 0;    
        tempoentreframes = 200;  // Tempo entre as trocas de frame da animação
    }
    
    public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
        // Desenha a imagem do personagem na tela
        dbg.drawImage(
            AnimeSet, 
            (int) getPosX() - XMundo, 
            (int) getPosY() - YMundo, 
            (int) (getPosX() + sizeX) - XMundo, 
            (int) (getPosY() + sizeY) - YMundo, 
            sizeX * frame, 
            sizeY * animacao, 
            (sizeX * frame) + sizeX, 
            (sizeY * animacao) + sizeY, 
            null
        );
    }

    public void SimulaSe(int DiffTime) {
        // Lógica de simulação do personagem (movimento, animação, etc.)
        if (segueobjetivo) {
            // Lógica para seguir um objetivo (caso seja necessário, poderia ser o cálculo do movimento)
            double dx = objetivoX - getPosX();
            double dy = objetivoY - getPosY();
            double distancia = Math.sqrt(dx * dx + dy * dy);
            if (distancia > 2) {
                ang = Math.atan2(dy, dx);
                setPosX(getPosX() + (int)(Math.cos(ang) * velocidade * DiffTime / 1000.0));
                setPosY(getPosY() + (int)(Math.sin(ang) * velocidade * DiffTime / 1000.0));
            } else {
                segueobjetivo = false;  // Chegou ao objetivo
            }
        }

        // Atualização do timer de animação
        timeranimacao += DiffTime;
        if (timeranimacao >= tempoentreframes) {
            frame = (frame + 1) % 4;  // Atualiza o frame da animação (supondo que o personagem tenha 4 quadros de animação)
            timeranimacao = 0;
        }
    }

    // Métodos para controlar o objetivo
    public void setObjetivo(double x, double y) {
        this.objetivoX = x;
        this.objetivoY = y;
        this.segueobjetivo = true;
    }

    public boolean isSeguindoObjetivo() {
        return segueobjetivo;
    }
}
