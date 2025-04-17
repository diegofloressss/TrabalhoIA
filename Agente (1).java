public class Agente {
    private int posX;
    private int posY;

    // Construtor da classe Agente
    public Agente(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    // Getter para a posição X
    public int getPosX() {
        return posX;
    }

    // Getter para a posição Y
    public int getPosY() {
        return posY;
    }

    // Setter para a posição X
    public void setPosX(int posX) {
        if (posX >= 0) { // Exemplo de validação
            this.posX = posX;
        } else {
            System.out.println("Posição X não pode ser negativa.");
        }
    }

    // Setter para a posição Y
    public void setPosY(int posY) {
        if (posY >= 0) { // Exemplo de validação
            this.posY = posY;
        } else {
            System.out.println("Posição Y não pode ser negativa.");
        }
    }

    // Método para mover o agente (exemplo simples)
    public void mover(int novoX, int novoY) {
        if (novoX >= 0 && novoY >= 0) { // Validação simples para evitar valores negativos
            this.posX = novoX;
            this.posY = novoY;
        } else {
            System.out.println("Posições inválidas! O agente não pode se mover para coordenadas negativas.");
        }
    }

    // Representação em string para exibir o estado do agente
    @Override
    public String toString() {
        return "Agente{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
