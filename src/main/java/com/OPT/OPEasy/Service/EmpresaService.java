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
        if(hasEmpresaByNick(empresa.getNick()))
            throw new ResourceNotFoundException("Nick informado já se encontra cadastrado. Tente outro.");
        newEmpresa.setAttributes(empresa);
        empresaRepository.save(newEmpresa);   
        return newEmpresa;
    }

    public Empresa updateEmpresa(Long id, Empresa empresa) throws Exception{
        Empresa empresaFound = checkEmpresaUpdate(id, empresa);
        empresaFound.setAttributes(empresa);
        empresaRepository.save(empresaFound);
        return empresaFound;
    }

    public Empresa deleteEmpresa(Long id){
        Empresa empresaFound = empresaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        empresaRepository.delete(empresaFound);
        return empresaFound;
    }

    public Empresa getEmpresaByID(Long id){
        Empresa empresa = empresaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        return empresa;
    }
    public Empresa getEmpresaByNick(String nick){
        Empresa empresa = empresaRepository.findByNick(nick).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        return empresa;
    }

    public Stream<Empresa> findAll(){
        return empresaRepository.findAll().stream();
    }
    
    public boolean hasEmpresaById(Long id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.isPresent();
    }

    public boolean hasEmpresaByNick(String nick){
        return empresaRepository.findByNick(nick).isPresent();
    }

        //Checa se o esta tentando alterar o nick do empresa,
    //Se estiver, checa se existe outro empresa com o nick, 
    //caso exista, retorna erro, no contrário, o empresa é alterado 
    public Empresa checkEmpresaUpdate(Long id, Empresa empresa) throws Exception{
        Empresa empresaFound = empresaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

        if(empresa.getNick() != null){
            String nick = empresa.getNick();

            if(hasEmpresaByNick(nick)){
                Empresa empresaByNick = empresaRepository.findByNick(nick).get();
                if(empresaByNick.getId() == empresaFound.getId()){
                    return empresaFound;
                } else{
                    throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");
                }
            }
        }
        return empresaFound;
    }
}
