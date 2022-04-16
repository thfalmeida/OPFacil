package com.OPT.OPEasy.Service;

import java.util.Optional;
import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Empresa;
import com.OPT.OPEasy.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa cadastrarEmpresa(Empresa empresa){
        Empresa newEmpresa = new Empresa();
        newEmpresa.setAttributes(empresa);
        empresaRepository.save(newEmpresa);   
        return newEmpresa;
    }

    public Empresa updateEmpresa(Long id, Empresa empresa){
        Empresa empresaFound = empresaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Empresa não encontrada"));
        empresaFound.setAttributes(empresa);
        empresaRepository.save(empresaFound);
        return empresaFound;
    }

    public Empresa deleteEmpresa(Long id){
        Empresa empresaFound = empresaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Empresa não encontrada"));
        empresaRepository.delete(empresaFound);
        return empresaFound;
    }

    public Empresa getEmpresaByID(Long id){
        Empresa empresa = empresaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Empresa não encontrada"));
        return empresa;
    }
    public Empresa getEmpresaByID(String nick){
        Empresa empresa = empresaRepository.findByNick(nick).orElseThrow(
            () -> new ResourceNotFoundException("Empresa não encontrada"));
        return empresa;
    }

    public Stream<Empresa> findAll(){
        return empresaRepository.findAll().stream();
    }
    
    public boolean hasEmpresaById(Long id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.isPresent();
    }
}
