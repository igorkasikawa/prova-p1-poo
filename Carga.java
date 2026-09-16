import java.util.Locale;
import java.util.Objects;
public class Carga {

    private final String codigoRastreio;
    private String categoria;
    private double pesokg;
    private double valorSeguro;

    public Carga(String codigoRastreio,String categoria, double pesokg, double valorSeguro) {
        if (codigoRastreio == null || codigoRastreio.trim().isEmpty()) {
            throw new illegalArgumentException("Codigo de rastreio da carga nao pode ser nuo ou vazio.");
        }
        this.codigoRastreio = codigoRastreio;
        this.categoria = categoria;
        this.pesokg = pesokg;
        this.valorSeguro = valorSeguro;

        public String getCodigoRastreio(){
            return codigoRastreio;
        }
        public String getCategoria(){
            return categoria;
        }
        public double getPesokg(){
            return pesokg;
        }
        public double getValorSeguro(){
            return valorSeguro;
        }

        public void setCategoria(String categoria){
            this.categoria = categoria;
        }
        public void setPesokg(double pesokg){
            this.pesokg = pesokg;
        }
        public void setValorSeguro(double valorSeguro){
            this.valorSeguro = valorSeguro;
        }
        //duas cargas sao iguais quando possuem o mesmo codigo

        @Override
        public boolean equals(object obj){
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Carga)) {
                return false
            }
            Carga outra = (Carga) obj;
            return Object.equals(
                this.codigoRastreio,outra.codigoRastreio
            );
        }
        //O hash utiliza o mesmo campo empregado em equals

        @Override
        public int hashCode(){
            return Objects.hash(codigoRastreio);
        }
    }

}