import java.util.Map;  
import java.util.HashMap;

public class IdAutomatico {
    // Mapa para armazenar o contador de IDs para cada classe
    private static final Map<Class<?>, Integer> contadorPorClasse = new HashMap<>();
    private int id;

    // Construtor
    public IdAutomatico() {
        // Obtém a classe da instância atual
        Class<?> classe = this.getClass();
        // Obtém o contador para a classe atual ou cria um novo contador se não existir
        contadorPorClasse.putIfAbsent(classe, 0);
        // Incrementa o contador e atribui o valor ao atributo id
        this.id = contadorPorClasse.get(classe);
        // Incrementa o contador para a próxima instância da mesma classe
        contadorPorClasse.put(classe, this.id + 1);
    }

    public int getId() {
        return id;
    }
}
