package com.jcg.hibernate.crud.operations;

import java.util.ArrayList;
import java.util.List;

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
}
