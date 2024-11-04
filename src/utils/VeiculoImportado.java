package utils;

public class VeiculoImportado extends Veiculo {
	 public VeiculoImportado(String placa, String marca, String cor) {
	        super(placa, marca, cor);
	    }

	    @Override
	    public String getTipo() {
	        return "Importado";
	    }
}
