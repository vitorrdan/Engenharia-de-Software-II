package com.jcg.hibernate.crud.operations;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbOperations {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    public final static Logger logger = Logger.getLogger(DbOperations.class);

    // Cria a SessionFactory do Hibernate
    private static SessionFactory buildSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    // Método 1: Criar registros de Médico no banco de dados
    public static void createRecord() {
        int count = 0;
        Medico medicoObj = null;
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            // Criando registros de Médico
            for(int j = 1; j <= 5; j++) {
                count = count + 1;
                medicoObj = new Medico();
                medicoObj.setNome("Médico " + j);
                medicoObj.setSalario("5000");
                medicoObj.setEspecialidade("Clínico Geral");
                sessionObj.save(medicoObj);
            }

            sessionObj.getTransaction().commit();
            logger.info("\nSucesso na criação de '" + count + "' registros no banco de dados!\n");
        } catch(Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transação sendo revertidar.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Método 2: Exibir registros de Médico do banco de dados
    @SuppressWarnings("unchecked")
    public static List<Medico> displayRecords() {
        List<Medico> medicosList = new ArrayList<>();
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            medicosList = sessionObj.createQuery("FROM Medico").list();
        } catch(Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transação sendo revertida.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
        return medicosList;
    }

    // Método 3: Atualizar um registro de Médico no banco de dados
    public static void updateRecord(int crm) {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            Medico medicoObj = (Medico) sessionObj.get(Medico.class, crm);
            medicoObj.setNome("Dr. Atualizado");
            medicoObj.setSalario("7000");

            sessionObj.getTransaction().commit();
            logger.info("\nMédico com CRM?= " + crm + " atualizado com sucesso no banco de dados!\n");
        } catch(Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transação sendo revertida.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Método 4(a): Deletar um registro de Médico no banco de dados
    public static void deleteRecord(Integer crm) {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            Medico medicoObj = findRecordById(crm);
            sessionObj.delete(medicoObj);

            sessionObj.getTransaction().commit();
            logger.info("\nMédico com CRM?= " + crm + " deletado com sucesso do banco de dados!\n");
        } catch(Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transação sendo revertida.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Método 4(b): Buscar um registro de Médico pelo CRM no banco de dados
    public static Medico findRecordById(Integer crm) {
        Medico findMedicoObj = null;
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            findMedicoObj = (Medico) sessionObj.get(Medico.class, crm);
        } catch(Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transação sendo revertida.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findMedicoObj;
    }

    // Método 5: Deletar todos os registros de Médico no banco de dados
    public static void deleteAllRecords() {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM Medico");
            queryObj.executeUpdate();

            sessionObj.getTransaction().commit();
            logger.info("\nTodos os registros de Médico foram deletados do banco de dados!\n");
        } catch(Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transação sendo revertida.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

	/*
	 * CRUD - PACIENTE
	 */

	 public static void createPacienteRecord() {
		int count = 0;
		Paciente pacienteObj = null;
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
	
			// Criando registros de Paciente
			for(int j = 1; j <= 5; j++) {
				count = count + 1;
				pacienteObj = new Paciente();
				pacienteObj.setCPF("CPF_" + j);
				pacienteObj.setNome("Paciente " + j);
				pacienteObj.setEndereco("Endereco " + j);
				pacienteObj.setTelefone("123456789");
				pacienteObj.setData_Nasc(Date.valueOf("1990-01-01"));
				pacienteObj.setQuarto(101 + j);
				sessionObj.save(pacienteObj);
			}
	
			sessionObj.getTransaction().commit();
			logger.info("\nSucesso na criação de '" + count + "' registros de Paciente no banco de dados!\n");
		} catch(Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transação sendo revertida.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
public static List<Paciente> displayPacienteRecords() {
    List<Paciente> pacienteList = new ArrayList<>();
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        pacienteList = sessionObj.createQuery("FROM Paciente").list();
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
    return pacienteList;
}


public static void updatePacienteRecord(String cpf) {
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        Paciente pacienteObj = (Paciente) sessionObj.get(Paciente.class, cpf);
        pacienteObj.setNome("Paciente Atualizado");
        pacienteObj.setEndereco("Endereco Atualizado");

        sessionObj.getTransaction().commit();
        logger.info("\nPaciente com CPF?= " + cpf + " atualizado com sucesso no banco de dados!\n");
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
}


public static void deletePacienteRecord(String cpf) {
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        Paciente pacienteObj = findPacienteById(cpf);
        sessionObj.delete(pacienteObj);

        sessionObj.getTransaction().commit();
        logger.info("\nPaciente com CPF?= " + cpf + " deletado com sucesso do banco de dados!\n");
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
}

public static Paciente findPacienteById(String cpf) {
    Paciente findPacienteObj = null;
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        findPacienteObj = (Paciente) sessionObj.get(Paciente.class, cpf);
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    }
    return findPacienteObj;
}



/*
 * CRUD - Tratamento
 */

 public static void createTratamentoRecord() {
    int count = 0;
    Tratamento tratamentoObj = null;
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        // Criando registros de Tratamento
        for(int j = 1; j <= 5; j++) {
            count = count + 1;
            tratamentoObj = new Tratamento();
            tratamentoObj.setCPF("CPF_" + j);
            tratamentoObj.setCRM("CRM_" + j);
            tratamentoObj.setResponsavel(j % 2 == 0);
            sessionObj.save(tratamentoObj);
        }

        sessionObj.getTransaction().commit();
        logger.info("\nSucesso na criação de '" + count + "' registros de Tratamento no banco de dados!\n");
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
}


@SuppressWarnings("unchecked")
public static List<Tratamento> displayTratamentoRecords() {
    List<Tratamento> tratamentoList = new ArrayList<>();
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        tratamentoList = sessionObj.createQuery("FROM Tratamento").list();
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
    return tratamentoList;
}


public static void updateTratamentoRecord(String cpf, String crm) {
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        Tratamento tratamentoObj = (Tratamento) sessionObj.get(Tratamento.class, cpf);
        tratamentoObj.setCRM(crm);
        tratamentoObj.setResponsavel(true);

        sessionObj.getTransaction().commit();
        logger.info("\nTratamento com CPF?= " + cpf + " atualizado com sucesso no banco de dados!\n");
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
}


public static void deleteTratamentoRecord(String cpf) {
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        Tratamento tratamentoObj = findTratamentoById(cpf);
        sessionObj.delete(tratamentoObj);

        sessionObj.getTransaction().commit();
        logger.info("\nTratamento com CPF?= " + cpf + " deletado com sucesso do banco de dados!\n");
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
}


public static Tratamento findTratamentoById(String cpf) {
    Tratamento findTratamentoObj = null;
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        findTratamentoObj = (Tratamento) sessionObj.get(Tratamento.class, cpf);
    } catch(Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    }
    return findTratamentoObj;
}



public static void createSingleMedicoRecord() {
    Medico medicoObj = new Medico();
    try {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        
        medicoObj.setNome("Dr. João Silva");
		medicoObj.setCRM("145bc");
        medicoObj.setSalario("8000");
        medicoObj.setEspecialidade("Cardiologista");

        sessionObj.save(medicoObj);  // Salvando o médico no banco de dados

        sessionObj.getTransaction().commit();
        logger.info("\nSucesso na criação do médico: " + medicoObj.toString() + " no banco de dados!\n");
    } catch (Exception sqlException) {
        if (null != sessionObj.getTransaction()) {
            logger.info("\n.......Transação sendo revertida.......\n");
            sessionObj.getTransaction().rollback();
        }
        sqlException.printStackTrace();
    } finally {
        if (sessionObj != null) {
            sessionObj.close();
        }
    }
}


public static void main(String[] args) {
    logger.info(".......Teste de criação de um único médico.......\n");

    // Criando um único registro de médico
    DbOperations.createSingleMedicoRecord();

    // Exibindo os registros de médicos para confirmar a inserção
    List<Medico> medicos = DbOperations.displayRecords();
    if (medicos != null && !medicos.isEmpty()) {
        for (Medico medico : medicos) {
            logger.info(medico.toString());
        }
    } else {
        logger.info("Nenhum médico encontrado no banco de dados.");
    }

    System.exit(0);
}




}
