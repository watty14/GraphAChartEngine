package android.graph;

public class Menu {

    private boolean menuMain;
    private String menuName;
    private int menuLike;
    
    public Menu() {
    }
    
    public boolean getMenuMain() {
        return menuMain;
    }
    
    public String getMenuName() {
        return menuName;
    }
    
    public int getMenuLike() {
        return menuLike;
    }
    
    public void setMenuMain(boolean menuMain) {
        this.menuMain = menuMain;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    public void setMenuLike(int menuLike) {
        this.menuLike = menuLike;
    }
}
