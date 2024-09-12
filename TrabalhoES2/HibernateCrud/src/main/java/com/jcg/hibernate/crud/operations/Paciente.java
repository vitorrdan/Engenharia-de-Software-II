package com.jcg.hibernate.crud.operations;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Paciente1458060")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPF")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String CPF;

	@Column(name="Nome")
	private String Nome;

	@Column(name="Endereco")
	private String Endereco;

	@Column(name="Telefone")
	private String Telefone;

    @Column(name="Data_Nasc")
	private Date Data_Nasc;

	@Column(name="Quarto")
	private int Quarto;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getName() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String Endereco) {
		this.Endereco = Endereco;
	}

    public void setTelefone(String telefone){
        this.Telefone = telefone;
    }

	public String getTelefone() {
		return Telefone;
	}

    public Date getData_Nasc() {
		return Data_Nasc;
	}

	public void setData_Nasc(Date nasc) {
		this.Data_Nasc = nasc;
	}

    public void setQuarto(int quarto){
        this.Quarto = quarto;
    }

	public int getQuarto() {
		return Quarto;
	}

	
	@Override
	public String toString() {
		return "Paciente Details?= CPF: " + this.CPF + ", Nome: " + this.Nome + ", Endereco: " + this.Endereco + ", Telefone: " + this.Telefone + ", Data_Nasc:" + this.Data_Nasc + ", Quarto: " + this.Quarto;
	}
}