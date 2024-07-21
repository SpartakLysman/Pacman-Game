package pacmanGame.models;

import pacmanGame.Constants;
import pacmanGame.screens.gameScreen.GameModel;

public class BonusModel extends BaseModel{
    public BonusModel(GameModel model) {
        super(model);
        String imageName = resolveImageName(model);
        setFileName(imageName);
    }

    private String resolveImageName(GameModel model) {
        return Constants.EMPTY_SPACE_FILE_NAME;
    }
}
