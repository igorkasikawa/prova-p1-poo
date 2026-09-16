import br.com.starlog.exception.CapacidadeExcedidaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class ModuloCarga {
    private String codigoModulo;
    private int capacidadeMaxima;
    private List<Carga> cargas;
    // Todo módulo começa com uma lista vazia.
    public ModuloCarga(String codigoModulo, int capacidadeMaxima) {
        this.codigoModulo = codigoModulo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.cargas = new ArrayList<>();
    }
    public String getCodigoModulo() {
        return codigoModulo;
    }
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    // Retorna uma cópia para proteger a lista interna.
    public List<Carga> getCargas() {
        return new ArrayList<>(cargas);
    }
    // Verifica a capacidade antes de inserir a carga.
    public void carregarCarga(Carga carga)
            throws CapacidadeExcedidaException {
        if (cargas.size() >= capacidadeMaxima) {
            throw new CapacidadeExcedidaException(
                "Modulo '" + codigoModulo
                + "' atingiu a capacidade maxima de "
                + capacidadeMaxima + " cargas."
            );
        }
        cargas.add(carga);
    }
    // Soma o seguro de todas as cargas armazenadas.
    public double calcularSeguroTotal() {
        return cargas.stream()
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }
    // Conta somente cargas da categoria informada.
    public long contarCargasPorCategoria(String categoria) {
        return cargas.stream()
                .filter(carga ->
                    Objects.equals(categoria, carga.getCategoria())
                )
                .count();
    }
    // Soma seguros somente quando os dois critérios são atendidos:
    // mesma categoria E peso estritamente superior ao mínimo.
    public double calcularSeguroCargasPesadas(
            String categoria, double pesoMinimo) {
        return cargas.stream()
                .filter(carga ->
                    Objects.equals(categoria, carga.getCategoria())
                )
                .filter(carga -> carga.getPesoKg() > pesoMinimo)
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }
}

import java.util.HashMap;
import java.util.Map;
public class BaseLancamento {
    private Map<String, ModuloCarga> modulos;
    public BaseLancamento() {
        this.modulos = new HashMap<>();
    }
    // Usa o código do módulo como chave do cadastro.
    public void cadastrarModulo(ModuloCarga modulo) {
        modulos.put(modulo.getCodigoModulo(), modulo);
    }
    // Retorna o módulo correspondente ao código.
    public ModuloCarga buscarModulo(String codigoModulo) {
        return modulos.get(codigoModulo);
    }
    // Getter exigido pelo diagrama; devolve uma cópia do Map.
    public Map<String, ModuloCarga> getModulos() {
        return new HashMap<>(modulos);
    }
}
