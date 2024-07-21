package pacmanGame.models.wallModels;

import pacmanGame.Constants;
import pacmanGame.models.BaseModel;
import pacmanGame.screens.gameScreen.GameModel;

public class WallModel extends BaseModel {

    public WallModel(GameModel model) {
        super(model);
        String imageName = resolveImageName(model);
        setFileName(imageName);
    }

    private String resolveImageName(GameModel model) {
        return Constants.WALL_FILE_NAME;
    }

    @Override
    public int getImageSize() {
        return Constants.DEFAULT_CELL_SIZE * model.getScreenScale();
    }
}
