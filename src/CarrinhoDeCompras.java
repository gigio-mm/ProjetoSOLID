import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {

    private final Map<Produto, Integer> itens;

    public CarrinhoDeCompras() {
        this.itens = new HashMap<>();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return;
        }
        int quantidadeAtual = this.itens.getOrDefault(produto, 0);
        this.itens.put(produto, quantidadeAtual + quantidade);
    }

    public void removerItem(Produto produto) {
        if (produto != null) {
            this.itens.remove(produto);
        }
    }

    public Map<Produto, Integer> getItens() {
        return Collections.unmodifiableMap(this.itens);
    }

    public double calcularTotal() {
        double total = 0.0;
        
        for (Map.Entry<Produto, Integer> entry : this.itens.entrySet()) {
            Produto produto = entry.getKey();
            Integer quantidade = entry.getValue();
            total += produto.getPreco() * quantidade;
        }
        
        return total;
    }
}