package edu.elsmancs.numeralRoman.test;

import edu.elsmancs.numeralRoman.NumerosRomanos;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class NumeroRomanosTest {

    public static NumerosRomanos numeroRomano;

    @BeforeClass
    public static void setup() {
        numeroRomano = new NumerosRomanos();
        numeroRomano.initRegexDicionario();
    }

    /**
     * Grupos sumatorios M, C, X, I
     */

    @Test
    public void grupo_M_test() {

        String testCase = "M";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(1000, numeroRomano.toDecimal());

        testCase = "UMMU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(2000, numeroRomano.toDecimal());

        testCase = "UMMMU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(3000, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano.setNumeroLetra(testCase);
        assertNotEquals(1000, numeroRomano.toDecimal());

        /**
         * El caso MMMM es control de errores
         * y no puede estas en el test de la logica
         * Asumimos que la entrada es correcta.
         * Sino, hay que programar la gestion de errores
         */
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "UMMMUCCCU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(3300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "UMMMUXXXU";
        numeroRomano.setNumeroLetra(testCase);

        assertEquals(3030, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "UMMMUIIIU";
        numeroRomano.setNumeroLetra(testCase);

        assertEquals(3003, numeroRomano.toDecimal());
    }

    @Test
    public void una_D_test() {

        String testCase = "UMMMUDUIIIU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(3503, numeroRomano.toDecimal());

        testCase = "MMMUCDUIIIU";
        numeroRomano.setNumeroLetra(testCase);
        assertNotEquals(3503, numeroRomano.toDecimal());
    }

    /**
     * Grupos substractivos
     * IV(4), IX(9),
     * XL(40), XC(90),
     * CD(400), CM(900)
     */

    @Test
    public void grupo_C_DM_test() {

        String testCase = "UCDU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(400, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(900, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "UXLU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(40, numeroRomano.toDecimal());

        testCase = "UXCU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(90, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "UIVU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(4, numeroRomano.toDecimal());

        testCase = "UIXU";
        numeroRomano.setNumeroLetra(testCase);
        assertEquals(9, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_sumatorios_tres_digitos_test() {
        String test = "MMMDCCCLXXXVIII"; // 3888
        numeroRomano.setNumeroLetra(test);
        assertEquals(3888, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_sumatorios_test() {
        String test = "MMDCCLXXVII"; // 2777
        numeroRomano.setNumeroLetra(test);
        assertEquals(2777, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_substractivos_test() {
        String test = "CDXLIV"; // 444
        numeroRomano.setNumeroLetra(test);
        assertEquals(444, numeroRomano.toDecimal());

        test = "CDXXXIX"; // 439
        numeroRomano.setNumeroLetra(test);
        assertEquals(439, numeroRomano.toDecimal());
    }

    @Test
    public void initArrayRegex_test() {
        String test = "V";
        numeroRomano.setNumeroLetra(test);
        assertEquals(2, numeroRomano.getRegexMapa().longitud());
        assertEquals(5, numeroRomano.valorDecimal(test));
        assertEquals("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])", numeroRomano.getRegexMapa().getRegexValue("grupoSumatorio"));
        assertEquals("(C[DM])|(X[LC])|(I[VX])", numeroRomano.getRegexMapa().getRegexValue("grupoSustractivo"));
    }

    @Test
    public void toDecimal() {
        String test = "V";
        numeroRomano.setNumeroLetra(test);
        assertEquals(2, numeroRomano.getExpresionesRegulares().size());
        assertTrue(numeroRomano.getRegexMapa().getValues().contains("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])"));
        assertTrue(numeroRomano.getRegexMapa().getValues().contains("(C[DM])|(X[LC])|(I[VX])"));
    }

    @Test
    public void valorDecimal_test() {
        String test = "V";
        numeroRomano.setNumeroLetra(test);
        assertEquals(2, numeroRomano.getRegexMapa().getRegex().size());
        assertEquals(5, numeroRomano.valorDecimal(test));

        test = "IV";
        numeroRomano.setNumeroLetra(test);
        assertEquals(4, numeroRomano.valorDecimal(test));

        test = "CM";
        numeroRomano.setNumeroLetra(test);
        assertEquals(900, numeroRomano.valorDecimal(test));

        /**
         *  test = "U";
         * numeroRomano.setNumeroRomano("U");
         * assertEquals(900, numeroRomano.valorDecimal(test));
         */
    }
}
