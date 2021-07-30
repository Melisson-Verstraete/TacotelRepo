package model;

public class Categorie {

    private int IdCategorie;
    private String NomCategorie;

    public Categorie(int idCategorie, String nomCategorie) {
        IdCategorie = idCategorie;
        NomCategorie = nomCategorie;
    }

    public Categorie(String nomCategorie) {
        NomCategorie = nomCategorie;
    }

    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        IdCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        NomCategorie = nomCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "IdCategorie=" + IdCategorie +
                ", NomCategorie='" + NomCategorie + '\'' +
                '}';
    }
}
