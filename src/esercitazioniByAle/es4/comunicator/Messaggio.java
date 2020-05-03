package esercitazioniByAle.es4.comunicator;

import java.util.Date;

public class Messaggio {
    private String messaggio;
    private Comunicator mittente;
    private Date dataRicezione;

    public Messaggio(String messaggio, Comunicator mittente, Date dataRicezione) {
        this.messaggio = messaggio;
        this.mittente = mittente;
        this.dataRicezione = dataRicezione;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public Comunicator getMittente() {
        return mittente;
    }

    public Date getDataRicezione() {
        return dataRicezione;
    }

    @Override
    public String toString() {
        return "{ [messaggio='" + messaggio + '\'' +
                "], mittente=" + mittente +
                ", dataRicezione=" + dataRicezione +
                '}';
    }
}
