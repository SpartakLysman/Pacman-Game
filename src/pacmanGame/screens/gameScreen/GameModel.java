package pacmanGame.screens.gameScreen; //model-view-controller

import pacmanGame.Constants;
import pacmanGame.models.*;
import pacmanGame.models.wallModels.WallModel;
import pacmanGame.shapes.*;
import pacmanGame.shapes.wallShapes.WallShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {

    private List<Ghost> ghosts = new ArrayList<>();
    private Pacman pacman;
    private GameShape[][] map;
    private FieldPanel field;
    private int mapWidth;
    private int mapHeight;
    private int cellSize;
    private final int[][] mapTemplate;

    public GameModel(FieldPanel field, int mapWidth, int mapHeight, int cellSize) {
        mapTemplate = selectMapTemplate();
        this.field = field;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.cellSize = cellSize;
        generateGhosts();
        pacman = new Pacman(new PacmanModel(this));
        setPacmanInitialPosition();
        generateMap();
    }

    private void setPacmanInitialPosition() {
        int startX = cellSize;
        int startY = cellSize;

        pacman.moveTo(startX, startY);
    }

//    public boolean isPacmanCanMove(Direction direction) {
//        return pacman.isCanMove(direction);
//    }

    public boolean isWallForward(float x, float y, Direction direction) {
        int cellX = Math.round((x - 1) / cellSize);
        int cellY = Math.round((y - 1) / cellSize);

        switch (direction) {
            case UP:
                cellX = Math.round((x - 1) / cellSize);
                cellY = Math.round((y - 1) / cellSize);
                cellY -= 1;
                break;
            case DOWN:
                cellX = Math.round((x - 1) / cellSize);
                cellY = Math.round((y) / cellSize);
                //  cellY += 1;
                break;
            case LEFT:
                cellX = Math.round((x - 1) / cellSize);
                cellY = Math.round((y - 1) / cellSize);
                cellX -= 1;
                break;
            case RIGHT:
                cellX = Math.round((x) / cellSize);
                cellY = Math.round((y - 1) / cellSize);
                 //cellX += 1;
                break;
            case NONE:
                return false;
        }

        if (cellX < 0 || cellX >= mapWidth || cellY < 0 || cellY >= mapHeight) {
            return true;
        }

        return mapTemplate[cellY][cellX] == 1 || mapTemplate[cellY][cellX] == 2;
    }

    public int[][] selectMapTemplate() {
        switch (mapWidth) {
            case 21:
                return GameMaps.LEVEL_1_21x21;
            case 31:
                return GameMaps.LEVEL_1_31x31;
            case 41:
                return GameMaps.LEVEL_1_41x41;
            case 51:
                return GameMaps.LEVEL_1_51x51;
            case 61:
                return GameMaps.LEVEL_1_61x61;
        }
        return GameMaps.LEVEL_1_21x21;
    }

    private void generateGhosts() {
        for (int i = 0; i < Constants.INITIAL_GHOSTS_COUNT; i++) {
            ghosts.add(new Ghost(new GhostModel(this)));
        }
    }

    private void generateMap() {
        map = new GameShape[mapWidth][mapHeight]; //21 X 21
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int cellType = mapTemplate[j][i];
                BaseModel model;
                switch (cellType) {
                    case 0:
                        model = new EmptySpaceModel(this);
                        model.moveTo(i * Constants.DEFAULT_CELL_SIZE, j * Constants.DEFAULT_CELL_SIZE);
                        map[i][j] = new EmptySpace(model);
                        break;
                    case 1:
                        model = new WallModel(this);
                        model.moveTo(i * Constants.DEFAULT_CELL_SIZE, j * Constants.DEFAULT_CELL_SIZE);
                        map[i][j] = new WallShape(model);
                        break;
                    case 2:
                        model = new WallModel(this);
                        model.moveTo(i * Constants.DEFAULT_CELL_SIZE, j * Constants.DEFAULT_CELL_SIZE);
                        map[i][j] = new WallShape(model);
                        break;
                    case 3:
                        model = new EmptySpaceModel(this);
                        model.moveTo(i * Constants.DEFAULT_CELL_SIZE, j * Constants.DEFAULT_CELL_SIZE);
                        map[i][j] = new EmptySpace(model);
                        break;
                }
            }
        }
    }

    public List<GameShape> getShapes() {
        List<GameShape> gameShapes = new ArrayList<>();

        if (map != null) {
            for (GameShape[] shapes : map) {
                gameShapes.addAll(Arrays.asList(shapes).subList(0, map[0].length));
            }
        }
        if (ghosts != null && !ghosts.isEmpty()) {
            gameShapes.addAll(ghosts);
        }
        gameShapes.add(pacman);

        return gameShapes;
    }

    public boolean isFreeSpace(int x, int y) {
     return mapTemplate[y][x] == 0;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getScreenScale() {
        return 1;  // field.getHeight() / Constants.DEFAULT_SCREEN_HEIGHT;
    }

    public int getCellSize() {
        return cellSize;
    }
}

