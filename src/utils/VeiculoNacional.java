package utils;

public class VeiculoNacional extends Veiculo {
	    private static final String[] CORES_PRIMARIAS = {"vermelho", "azul", "amarelo"};

	    public VeiculoNacional(String placa, String marca, String cor) throws IllegalArgumentException {
	        super(placa, marca, cor);
	        if (!isCorPrimaria(cor)) {
	            throw new IllegalArgumentException("Veículos nacionais só podem ter cores primárias (vermelho, azul, amarelo).");
	        }
	    }

	    private boolean isCorPrimaria(String cor) {
	        for (String corPrimaria : CORES_PRIMARIAS) {
	            if (corPrimaria.equalsIgnoreCase(cor)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    @Override
	    public String getTipo() {
	        return "Nacional";
	    }
}
