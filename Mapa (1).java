import java.awt.Graphics2D;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o mapa do jogo.
 * O mapa é carregado a partir de um arquivo de dados e renderizado com um tileset.
 */
public class Mapa {

    private static final int TILE_SIZE = 16; // Tamanho do tile em pixels
    private int largura;
    private int altura;
    private int numeroTilesX;
    private int numeroTilesY;
    private int mapX;
    private int mapY;
    private int tilePLinhaTileset; // Inicializada posteriormente

    private Image tileSet;
    private List<int[][]> camadas;

    /**
     * Construtor do mapa.
     * @param tileset Imagem do tileset a ser usado.
     * @param tilesX Número de tiles por linha no tileset.
     * @param tilesY Número de tiles por coluna no tileset.
     */
    public Mapa(Image tileset, int tilesX, int tilesY) {
        this.tileSet = tileset;
        this.numeroTilesX = tilesX;
        this.numeroTilesY = tilesY;
        this.camadas = new ArrayList<>();
        this.mapX = 0;
        this.mapY = 0;
        this.tilePLinhaTileset = tilesX; // Inicialização da variável
    }

    /**
     * Carrega o mapa a partir de um arquivo.
     * @param nomeMapa Nome do arquivo do mapa.
     */
    public void abreMapa(String nomeMapa) {
        try (InputStream in = getClass().getResourceAsStream(nomeMapa);
             DataInputStream data = new DataInputStream(in)) {

            int versao = data.readInt();
            this.largura = readCInt(data);
            this.altura = readCInt(data);
            int ltilex = readCInt(data);
            int ltiley = readCInt(data);
            byte[] nome = new byte[32];
            data.read(nome, 0, 32);
            int numLayers = readCInt(data);
            int numTiles = readCInt(data);
            int bytesPorTiles = readCInt(data);
            int vago1 = readCInt(data);
            int vago2 = readCInt(data);

            for (int i = 0; i < numLayers; i++) {
                int[][] camada = new int[altura][largura];
                if (bytesPorTiles == 1) {
                    carregarCamada(data, camada);
                } else if (bytesPorTiles == 2) {
                    carregarCamada(data, camada, 2);
                }
                camadas.add(camada);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + " - Erro ao abrir o mapa.");
        }
    }

    private void carregarCamada(DataInputStream data, int[][] camada) throws IOException {
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                camada[j][i] = data.readByte() & 0x00FF | (data.readByte() & 0x00FF) << 8;
            }
        }
    }

    private void carregarCamada(DataInputStream data, int[][] camada, int bytesPorTile) throws IOException {
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                int dado;
                if (bytesPorTile == 2) {
                    dado = (data.readByte() & 0x00FF) |
                            ((data.readByte() & 0x00FF) << 8) |
                            ((data.readByte() & 0x00FF) << 16) |
                            ((data.readByte() & 0x00FF) << 24);
                } else {
                    dado = data.readByte() & 0x00FF;
                }
                camada[j][i] = dado;
            }
        }
    }

    /**
     * Desenha o mapa na tela.
     * @param dbg Contexto gráfico para desenhar.
     */
    public void desenhaSe(Graphics2D dbg) {
        int offx = mapX & 0x0F;
        int offy = mapY & 0x0F;
        int somax = (offx > 0) ? 1 : 0;
        int somay = (offy > 0) ? 1 : 0;

        for (int camadaIndex = 0; camadaIndex < camadas.size(); camadaIndex++) {
            int[][] camada = camadas.get(camadaIndex);
            for (int j = 0; j < numeroTilesY + somay; j++) {
                for (int i = 0; i < numeroTilesX + somax; i++) {
                    int tileId = camada[j + (mapY >> 4)][i + (mapX >> 4)];
                    int tilex = (tileId % tilePLinhaTileset) << 4;
                    int tiley = (tileId / tilePLinhaTileset) << 4;
                    dbg.drawImage(tileSet, (i << 4) - offx, (j << 4) - offy,
                            (i << 4) + 16 - offx, (j << 4) + 16 - offy,
                            tilex, tiley, tilex + 16, tiley + 16, null);
                }
            }
        }
    }

    /**
     * Atualiza a posição do mapa com base nas coordenadas.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     */
    public void posiciona(int x, int y) {
        int X = x >> 4;
        int Y = y >> 4;

        mapX = Math.max(0, Math.min((largura - numeroTilesX), X)) << 4;
        mapY = Math.max(0, Math.min((altura - numeroTilesY), Y)) << 4;
    }

    private int readCInt(DataInputStream data) throws IOException {
        return data.readInt();
    }
}
