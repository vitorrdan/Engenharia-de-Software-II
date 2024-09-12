package com.jcg.hibernate.crud.operations;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Medico1458060")
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CRM")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String CRM;

	@Column(name="Nome")
	private String Nome;

	@Column(name="Salario")
	private String Salario;

	@Column(name="Especialidade")
	private String Especialidade;

	public String getCRM() {
		return CRM;
	}

	public void setCRM(String crm) {
		this.CRM = crm;
	}

	public String getName() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getSalario() {
		return Salario;
	}

	public void setSalario(String salario) {
		this.Salario = salario;
	}

	public String getEspecialidade() {
		return Especialidade;
	}

	public void setEspecialidade(String espe){
		this.Especialidade = espe;
	}

	
	@Override
	public String toString() {
		return "Medico Details?= CRM: " + this.CRM + ", Nome: " + this.Nome + ", Salario: " + this.Salario + ", Especialidade: " + this.Especialidade;
	}
}