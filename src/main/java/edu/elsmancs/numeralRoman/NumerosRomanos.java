package edu.elsmancs.numeralRoman;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumerosRomanos {

    private String numeroLetra = null;
    private short numeroDecimal = 0;

    private RegexNumerosRomanos regexMapa = new RegexNumerosRomanos();

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
        this.setNumeroDecimal((short) 0);
    }

    public short getNumeroDecimal() {
        return numeroDecimal;
    }

    public void setNumeroDecimal(short numeroDecimal) {
        this.numeroDecimal = numeroDecimal;
    }

    public void setRegexMapa(RegexNumerosRomanos regex) {
        this.regexMapa = regex;
    }

    public RegexNumerosRomanos getRegexMapa() {
        return this.regexMapa;
    }

    public void initRegexDicionario() {
        getRegexMapa().addRegex("grupoSumatorio", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
        getRegexMapa().addRegex("grupoSustractivo", "(C[DM])|(X[LC])|(I[VX])");
    }

    public void addRegex(String descripcion, String regex) {
        getRegexMapa().addRegex(descripcion, regex);
    }

    public List<String> getExpresionesRegulares() {
        List<String> listaRegex = new ArrayList<String>(getRegexMapa().getRegex().values());
        return listaRegex;
    }

    public short toDecimal() {
        for(String regex : getRegexMapa().getValues()) {
            Matcher matcher = createMatcher(regex);
            groupSumatoryToDecimal(matcher);
        }
        return getNumeroDecimal();
    }

    private Matcher createMatcher(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.numeroLetra);
        return matcher;
    }

    private void groupSumatoryToDecimal(Matcher matcher) {
        while (matcher.find()) {
            this.numeroDecimal += valorDecimal(matcher.group());
        }
    }

    public short valorDecimal(String numeroRomano) {
        SimbolosRomanos simbolo = Enum.valueOf(SimbolosRomanos.class, String.valueOf(numeroRomano));
        return (short) simbolo.getValorDecimal();
    }
}
