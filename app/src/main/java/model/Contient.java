package model;

public class Contient {

    private Panier panier;
    private Article article;
    private int qteArticleChoisi;

    public Contient(int qteArticleChoisi) {
        this.qteArticleChoisi = qteArticleChoisi;
    }

    public Contient() {
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQteArticleChoisi() {
        return qteArticleChoisi;
    }

    public void setQteArticleChoisi(int qteArticleChoisi) {
        this.qteArticleChoisi = qteArticleChoisi;
    }

    @Override
    public String toString() {
        return "Contient{" +
                "panier=" + panier +
                ", article=" + article +
                ", qteArticleChoisi=" + qteArticleChoisi +
                '}';
    }
}
