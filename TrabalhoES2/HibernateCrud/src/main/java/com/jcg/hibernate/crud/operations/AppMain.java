package com.jcg.hibernate.crud.operations;

import java.util.List;
import org.apache.log4j.Logger;


public class AppMain {

    public final static Logger logger = Logger.getLogger(AppMain.class);

    public static void main(String[] args) {
        logger.info(".......Hibernate CRUD Operations Example.......\n");

        // ====================== CRIANDO REGISTROS ======================

        logger.info("\n=======CRIANDO REGISTROS DE MÉDICOS=======\n");
        DbOperations.createRecord();

        logger.info("\n=======CRIANDO REGISTROS DE PACIENTES=======\n");
        DbOperations.createPacienteRecord();

        logger.info("\n=======CRIANDO REGISTROS DE TRATAMENTOS=======\n");
        DbOperations.createTratamentoRecord();

        // ====================== LENDO REGISTROS ======================

        logger.info("\n=======LENDO REGISTROS DE MÉDICOS=======\n");
        List<Medico> medicos = DbOperations.displayRecords();
        if (medicos != null && !medicos.isEmpty()) {
            for (Medico medico : medicos) {
                logger.info(medico.toString());
            }
        }

        logger.info("\n=======LENDO REGISTROS DE PACIENTES=======\n");
        List<Paciente> pacientes = DbOperations.displayPacienteRecords();
        if (pacientes != null && !pacientes.isEmpty()) {
            for (Paciente paciente : pacientes) {
                logger.info(paciente.toString());
            }
        }

        logger.info("\n=======LENDO REGISTROS DE TRATAMENTOS=======\n");
        List<Tratamento> tratamentos = DbOperations.displayTratamentoRecords();
        if (tratamentos != null && !tratamentos.isEmpty()) {
            for (Tratamento tratamento : tratamentos) {
                logger.info(tratamento.toString());
            }
        }

        // ====================== ATUALIZANDO REGISTROS ======================

        logger.info("\n=======ATUALIZANDO REGISTROS DE MÉDICOS=======\n");
        int medicoUpdateId = 1; // Por exemplo, atualizando o médico com CRM = 1
        DbOperations.updateRecord(medicoUpdateId);

        logger.info("\n=======LENDO REGISTROS DE MÉDICOS APÓS ATUALIZAÇÃO=======\n");
        medicos = DbOperations.displayRecords();
        for (Medico medico : medicos) {
            logger.info(medico.toString());
        }

        // ====================== DELETANDO REGISTROS ======================

        logger.info("\n=======DELETANDO REGISTRO DE MÉDICO=======\n");
        int medicoDeleteId = 5; // Por exemplo, deletando o médico com CRM = 5
        DbOperations.deleteRecord(medicoDeleteId);

        logger.info("\n=======LENDO REGISTROS DE MÉDICOS APÓS DELEÇÃO=======\n");
        medicos = DbOperations.displayRecords();
        for (Medico medico : medicos) {
            logger.info(medico.toString());
        }

        System.exit(0);
    }
}
