package edu.elsmancs.numeralRoman;

public class NumerosRomanos {

    private String numeroLetra = null;
    private int numeroDecimal = 0;

    private RegexNumerosRomanos regexDiccionario = new RegexNumerosRomanos();
    public NumerosRomanos() {
    };

    public NumerosRomanos(String numeroLetra) {
        this.numeroLetra = numeroLetra;
    }

    public String getNumeroLetra() {
        return numeroLetra;
    }

    public void setNumeroLetra(String numeroLetra) {
        this.numeroLetra = numeroLetra;
    }

    public int getNumeroDecimal() {
        return numeroDecimal;
    }

    public void setNumeroDecimal(int numeroDecimal) {
        this.numeroDecimal = numeroDecimal;
    }

    public void setRegexDiccionario(RegexNumerosRomanos regex) {
        this.regexDiccionario = regex;
    }
}
