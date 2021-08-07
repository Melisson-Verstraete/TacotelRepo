package model;

public class CompteBancaire {

    private String numCpte;
    private String typeCpte;
    private String titulaireCpte;

    public CompteBancaire(String numCpte, String typeCpte, String titulaireCpte) {
        this.numCpte = numCpte;
        this.typeCpte = typeCpte;
        this.titulaireCpte = titulaireCpte;
    }

    public String getNumCpte() {
        return numCpte;
    }

    public void setNumCpte(String numCpte) {
        this.numCpte = numCpte;
    }

    public String getTypeCpte() {
        return typeCpte;
    }

    public void setTypeCpte(String typeCpte) {
        this.typeCpte = typeCpte;
    }

    public String getTitulaireCpte() {
        return titulaireCpte;
    }

    public void setTitulaireCpte(String titulaireCpte) {
        this.titulaireCpte = titulaireCpte;
    }

    @Override
    public String toString() {
        return "CompteBancaire{" +
                "numCpte='" + numCpte + '\'' +
                ", typeCpte='" + typeCpte + '\'' +
                ", titulaireCpte='" + titulaireCpte + '\'' +
                '}';
    }
}
