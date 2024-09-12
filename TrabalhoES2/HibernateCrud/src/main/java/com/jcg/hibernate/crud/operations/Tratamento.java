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
@Table(name="Tratamento1458060")
public class Tratamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPF_Paciente")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String CPF;

    
	@Column(name="CMR_Medico")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String CRM;

	@Column(name="Responsavel")
	private boolean Responsavel;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getCRM() {
		return CRM;
	}

	public void setCRM(String CRM) {
		this.CRM = CRM;
	}

    public boolean getResponsavel(){
        return Responsavel;
    }

    public void setResponsavel(boolean resp){
        this.Responsavel = resp;
    }

	
	@Override
	public String toString() {
		return "Tratamento Details?= CPF: " + this.CPF + ", CRM: " + this.CRM + ", Responsavel: " + this.Responsavel;
	}
}