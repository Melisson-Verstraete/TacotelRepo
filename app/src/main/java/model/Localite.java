package model;

public class Localite {

    private int IdLocalite;
    private String Rue;
    private int CodePostal;
    private String NumRue;
    private String Ville;
    private String Pays;

    public Localite(int idLocalite, String rue, int codePostal, String numRue, String ville, String pays) {
        IdLocalite = idLocalite;
        Rue = rue;
        CodePostal = codePostal;
        NumRue = numRue;
        Ville = ville;
        Pays = pays;
    }

    public Localite(String rue, int codePostal, String numRue, String ville, String pays) {
        Rue = rue;
        CodePostal = codePostal;
        NumRue = numRue;
        Ville = ville;
        Pays = pays;
    }

    public int getIdLocalite() {
        return IdLocalite;
    }

    public void setIdLocalite(int idLocalite) {
        IdLocalite = idLocalite;
    }

    public String getRue() {
        return Rue;
    }

    public void setRue(String rue) {
        Rue = rue;
    }

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int codePostal) {
        CodePostal = codePostal;
    }

    public String getNumRue() {
        return NumRue;
    }

    public void setNumRue(String numRue) {
        NumRue = numRue;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    @Override
    public String toString() {
        return "Localite{" +
                "IdLocalite=" + IdLocalite +
                ", Rue='" + Rue + '\'' +
                ", CodePostal=" + CodePostal +
                ", NumRue='" + NumRue + '\'' +
                ", Ville='" + Ville + '\'' +
                ", Pays='" + Pays + '\'' +
                '}';
    }
}
