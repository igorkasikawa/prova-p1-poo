import br.com.starlog.exception.CapacidadeExcedidaException;
import br.com.starlog.model.BaseLancamento;
import br.com.starlog.model.Carga;
import br.com.starlog.model.ModuloCarga;
import java.util.HashSet;
import java.util.Locale;
public class MainControle {
    public static void main(String[] args) {
        // P01 - Criar quatro cargas.
        // Ordem: código, categoria, peso e seguro.
        Carga c1 = new Carga(
            "ORB-101-SP", "CRIOGENICA", 2.5, 450.00
        );
        Carga c2 = new Carga(
            "ORB-102-RJ", "PADRAO", 8.0, 120.00
        );
        Carga c3 = new Carga(
            "ORB-103-MG", "CRIOGENICA", 12.0, 850.00
        );
        Carga c4 = new Carga(
            "ORB-104-PR", "BIOLOGICA", 15.0, 300.00
        );
        // println utiliza o toString definido em Carga.
        System.out.println(c1);
        System.out.println(c4);
        // P02 - Criar um módulo com capacidade para três cargas.
        ModuloCarga modulo = new ModuloCarga(
            "MOD-ALFA-01", 3
        );
        BaseLancamento base = new BaseLancamento();
        base.cadastrarModulo(modulo);
        System.out.println(
            "Modulo '" + modulo.getCodigoModulo()
            + "' cadastrado na base com capacidade de "
            + modulo.getCapacidadeMaxima() + " cargas."
        );
        // P03 - Carregar as três primeiras cargas.
        // A mensagem de sucesso só aparece após a inclusão.
        try {
            modulo.carregarCarga(c1);
            System.out.println(
                "Carga " + c1.getCodigoRastreio()
                + " carregada no modulo com sucesso."
            );
            modulo.carregarCarga(c2);
            System.out.println(
                "Carga " + c2.getCodigoRastreio()
                + " carregada no modulo com sucesso."
            );
            modulo.carregarCarga(c3);
            System.out.println(
                "Carga " + c3.getCodigoRastreio()
                + " carregada no modulo com sucesso."
            );
        } catch (CapacidadeExcedidaException e) {
            System.out.println(
                "Excecao capturada: " + e.getMessage()
            );
        }
        // P04 - Tentar carregar a quarta carga no módulo cheio.
        try {
            modulo.carregarCarga(c4);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(
                "Excecao capturada: " + e.getMessage()
            );
        }
        // P05 - Recuperar o módulo cadastrado pelo seu código.
        ModuloCarga encontrado = base.buscarModulo("MOD-ALFA-01");
        if (encontrado != null) {
            System.out.println(
                "Modulo localizado na base: "
                + encontrado.getCodigoModulo()
            );
        }
        // P06 - Somar os seguros das cargas que entraram.
        System.out.printf(
            Locale.US,
            "Seguro total do modulo: R$ %.2f%n",
            modulo.calcularSeguroTotal()
        );
        // P07 - Contar as cargas criogênicas armazenadas.
        System.out.println(
            "Cargas CRIOGENICA: "
            + modulo.contarCargasPorCategoria("CRIOGENICA")
        );
        // P08 - Somar seguros das cargas criogênicas acima de 5 kg.
        System.out.printf(
            Locale.US,
            "Seguro de cargas criticas (CRIOGENICA > 5kg): R$ %.2f%n",
            modulo.calcularSeguroCargasPesadas("CRIOGENICA", 5.0)
        );
        // P09.1 - O HashSet deve ignorar o código repetido.
        HashSet<Carga> manifesto = new HashSet<>();
        manifesto.add(c1);
        manifesto.add(
            new Carga(
                "ORB-101-SP", "CRIOGENICA", 9.0, 990.00
            )
        );
        manifesto.add(c2);
        System.out.println(
            "Tamanho do manifesto (HashSet): " + manifesto.size()
        );
        // P09.2 - Confirmar a rejeição de código de rastreio vazio.
        try {
            new Carga("", "PADRAO", 1.0, 50.00);
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Construtor validado: " + e.getMessage()
            );
        }
    }
}
