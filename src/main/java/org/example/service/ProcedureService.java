package org.example.service;

import org.example.entity.Procedure;
import org.example.repository.ProcedureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProcedureService {
    private final ProcedureRepository procedureRepository;

    public ProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Procedure> findAllProceduresByMasterId(Long masterId){
        return procedureRepository.findByMaster(masterId);
    }

    public List<Procedure> findAllProceduresByCategory(Long categoryId) {
        return procedureRepository.findAllProceduresByCategoryId(categoryId);
    }

    public Optional<Procedure> findProcedureById(Long procedureId) {
        return procedureRepository.findById(procedureId);
    }
}
