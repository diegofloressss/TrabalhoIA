public class MeuAgente {
    private int posX;
    private int posY;
    private int velocidade;

    // Construtor da classe MeuAgente
    public MeuAgente(int x, int y, int velocidade) {
        this.posX = x;
        this.posY = y;
        this.velocidade = velocidade;
    }

    // Getter para a posição X
    public int getPosX() {
        return posX;
    }

    // Getter para a posição Y
    public int getPosY() {
        return posY;
    }

    // Getter para a velocidade
    public int getVelocidade() {
        return velocidade;
    }

    // Setter para a posição X
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Setter para a posição Y
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Setter para a velocidade
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    // Método para mover o agente, considerando a velocidade
    public void mover(int novoX, int novoY) {
        // Aqui você pode adicionar lógica de movimento considerando a velocidade, por exemplo
        this.posX = novoX + velocidade;
        this.posY = novoY + velocidade;
    }

    // Método para representar o agente de forma simples, como um ponto (posição)
    @Override
    public String toString() {
        return "MeuAgente{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", velocidade=" + velocidade +
                '}';
    }
}
