package utils;

public abstract class Veiculo {
	private String placa;
	private String marca;
	private String cor;
	
	
	public Veiculo(String placa, String marca, String cor) {
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
    }


	public Veiculo() {
		// TODO Auto-generated constructor stub
	}


	public String getPlaca() {
		return placa;
	}

	public String getMarca() {
		return marca;
	}

	public String getCor() {
		return cor;
	}
	

	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public abstract String getTipo();
		
}
