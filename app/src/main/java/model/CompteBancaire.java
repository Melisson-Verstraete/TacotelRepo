package model;

public class CompteBancaire {

    private String NumCpte;
    private String TypeCpte;
    private String TitulaireCpte;

    public CompteBancaire(String numCpte, String typeCpte, String titulaireCpte) {
        NumCpte = numCpte;
        TypeCpte = typeCpte;
        TitulaireCpte = titulaireCpte;
    }

    public String getNumCpte() {
        return NumCpte;
    }

    public void setNumCpte(String numCpte) {
        NumCpte = numCpte;
    }

    public String getTypeCpte() {
        return TypeCpte;
    }

    public void setTypeCpte(String typeCpte) {
        TypeCpte = typeCpte;
    }

    public String getTitulaireCpte() {
        return TitulaireCpte;
    }

    public void setTitulaireCpte(String titulaireCpte) {
        TitulaireCpte = titulaireCpte;
    }

    @Override
    public String toString() {
        return "CompteBancaire{" +
                "NumCpte='" + NumCpte + '\'' +
                ", TypeCpte='" + TypeCpte + '\'' +
                ", TitulaireCpte='" + TitulaireCpte + '\'' +
                '}';
    }
}
