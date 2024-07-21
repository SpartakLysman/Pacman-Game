package pacmanGame.models;

import pacmanGame.Constants;
import pacmanGame.screens.gameScreen.GameModel;
import pacmanGame.shapes.Point;

public class PointModel extends BaseModel{

    public PointModel(GameModel model) {
        super(model);
        String imageName = resolveImageName(model);
        setFileName(imageName);
    }

    private String resolveImageName(GameModel model) {
        return Constants.EMPTY_SPACE_FILE_NAME;
    }

    @Override
    public int getImageSize() {
        return 50 * model.getScreenScale();
    }
}
