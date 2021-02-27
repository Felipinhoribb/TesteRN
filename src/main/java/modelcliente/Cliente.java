package modelcliente;

import java.util.Date;

public class Cliente {

	//Atributos da Classe Aluno
	private long identificacao;
	private String nome;
	private String sexo;
	private int idade;
	private Date DataNascimento;
	private int nota1, nota2, nota3;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public long getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(long identificacao) {
		this.identificacao = identificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String string) {
		this.sexo = string;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Date getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		DataNascimento = dataNascimento;
	}

	public int getNota1() {
		return nota1;
	}

	public void setNota1(int nota1) {
		this.nota1 = nota1;
	}

	public int getNota2() {
		return nota2;
	}

	public void setNota2(int nota2) {
		this.nota2 = nota2;
	}

	public int getNota3() {
		return nota3;
	}

	public void setNota3(int nota3) {
		this.nota3 = nota3;
	}

	
	//Metodo de Sobrecarga ToString
	@Override
	public String toString() {
		return "Aluno [identificacao=" + identificacao + ", nome=" + nome + ", sexo=" + sexo + ", idade=" + idade
				+ ", DataNascimento=" + DataNascimento + ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3
				+ "]";
	}

}
