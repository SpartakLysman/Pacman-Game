package pacmanGame.models;

import pacmanGame.Constants;
import pacmanGame.screens.gameScreen.Direction;
import pacmanGame.screens.gameScreen.GameModel;

public class EmptySpaceModel extends BaseModel {

    public EmptySpaceModel(GameModel model) {
        super(model);
        String imageName = resolveImageName();
        setFileName(imageName);
    }

    private String resolveImageName() {
        return Constants.EMPTY_SPACE_FILE_NAME;
    }

    @Override
    public int getImageSize() {
        return model.getCellSize();
    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }
}
