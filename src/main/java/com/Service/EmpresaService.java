package com.Service;

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
        empresaRepository.save(empresa);   
        return newEmpresa;
    }

    public Exception updateEmpresa(Empresa empresa){
        return new Exception("Ainda não implementado");
    }
    
}
