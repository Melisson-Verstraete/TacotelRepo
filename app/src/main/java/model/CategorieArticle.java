package model;

public class CategorieArticle {

    private int IdArticle;
    private int IdCategorie;

    public CategorieArticle(int idArticle, int idCategorie) {
        IdArticle = idArticle;
        IdCategorie = idCategorie;
    }

    public int getIdArticle() {
        return IdArticle;
    }

    public void setIdArticle(int idArticle) {
        IdArticle = idArticle;
    }

    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        IdCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "CategorieArticle{" +
                "IdArticle=" + IdArticle +
                ", IdCategorie=" + IdCategorie +
                '}';
    }
}
