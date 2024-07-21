package pacmanGame.screens.menuScreen;

public class MenuModel { //Data for menu screen
    private String title;
    private String[] menuOptions;

    public MenuModel(String title, String[] menuOptions) {
        this.title = title;
        this.menuOptions = menuOptions;
    }

    public String getTitle() {
        return title;
    }

    public String[] getMenuOptions() {
        return menuOptions;
    }
}
