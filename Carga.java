import java.util.Locale;
import java.util.Objects;
public class Carga {
    private final String codigoRastreio;
    private String categoria;
    private double pesoKg;
    private double valorSeguro;
    // Recebe os dados e impede a criação de carga sem código.
    public Carga(String codigoRastreio, String categoria,
                 double pesoKg, double valorSeguro) {
        if (codigoRastreio == null
                || codigoRastreio.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Codigo de rastreio da carga nao pode ser nulo ou vazio."
            );
        }
        this.codigoRastreio = codigoRastreio;
        this.categoria = categoria;
        this.pesoKg = pesoKg;
        this.valorSeguro = valorSeguro;
    }
    // Getters permitem consultar os campos privados.
    public String getCodigoRastreio() {
        return codigoRastreio;
    }
    public String getCategoria() {
        return categoria;
    }
    public double getPesoKg() {
        return pesoKg;
    }
    public double getValorSeguro() {
        return valorSeguro;
    }
    // O código de rastreio não possui setter.
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }
    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
    // Duas cargas são iguais quando possuem o mesmo código.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Carga)) {
            return false;
        }
        Carga outra = (Carga) obj;
        return Objects.equals(
            this.codigoRastreio,
            outra.codigoRastreio
        );
    }
    // O hash utiliza o mesmo campo empregado em equals.
    @Override
    public int hashCode() {
        return Objects.hash(codigoRastreio);
    }
    // Peso com uma casa decimal; seguro com duas.
    @Override
    public String toString() {
        return String.format(
            Locale.US,
            "Carga[rastreio=%s, categoria=%s, peso=%.1fkg, seguro=R$ %.2f]",
            codigoRastreio,
            categoria,
            pesoKg,
            valorSeguro
        );
    }
}