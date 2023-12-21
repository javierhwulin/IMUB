package ub.edu.view;

public class MainListener implements UpdateListeners{
    private final EscenaMain escenaMain;

    public MainListener(EscenaMain escenaMain){
        this.escenaMain = escenaMain;
    }

    @Override
    public void update(String updateType) throws IllegalArgumentException{
        if (updateType.equals("wishList")) {
            escenaMain.popularWishList();
        } else if (updateType.equals("top10")) {
            escenaMain.popularTopDeuValorades();
        }else {
            throw new IllegalArgumentException("Tipus d'actualització no vàlida");
        }
    }
}
