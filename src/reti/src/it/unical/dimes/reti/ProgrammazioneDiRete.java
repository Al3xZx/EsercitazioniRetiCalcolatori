package reti.src.it.unical.dimes.reti;

/**
 * @author Fabrizio Marozzo <fmarozzo@dimes.unical.it>
 * @version 1.0, Oct 28, 2015
 */
public class ProgrammazioneDiRete extends RetiDiCalcolatori{

   public static final String ANNO = "2015/2016";

   public static void main(String[] args) {
     ProgrammazioneDiRete esercitazione =
                                new ProgrammazioneDiRete();
     System.out.println("Benvenuti all'esercitazione di "
      + esercitazione.getClass().getSuperclass().getSimpleName()
      + " dell'anno accademico " + ANNO);
  }
}
