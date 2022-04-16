package com.OPT.OPEasy.Service;

import java.util.Optional;
import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Empresa;
import com.OPT.OPEasy.model.Motorista;
import com.OPT.OPEasy.model.Viagem;
import com.OPT.OPEasy.repository.ViagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private EmpresaService empresaService;

    
    public Viagem cadastrarViagem(Viagem viagem){
        if(!motoristaService.hasMotoristaById(viagem.getMotoristaID()))
            throw new ResourceNotFoundException("Motorista não encontrado");
        if(viagem.getEmpresaID() != null && !empresaService.hasEmpresaById(viagem.getEmpresaID()))
            throw new ResourceNotFoundException("Empresa não encontrada");

        Viagem newViagem = new Viagem();
        newViagem.setAttributes(viagem);
        viagemRepository.save(newViagem);
        return newViagem;
    }
    
    public Viagem updateViagem(Long id,Viagem viagem){
        viagemRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Viagem não encontrada"));
        viagemRepository.save(viagem);
        return viagem;
    }

    public Viagem deleteViagem(Viagem viagem){
        viagemRepository.findById(viagem.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Viagem não encontrada"));
        viagemRepository.delete(viagem);
        return viagem;
    }

    public Stream<Viagem> findAll(){
        return viagemRepository.findAll().stream();
    }

    public Viagem getViagemById(Long id){
        Viagem viagem = viagemRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Viagem não encontrada"));
        return viagem;
    }

    public Stream<Viagem> getViagemByMotorista(Long motoristaID){
        Motorista motorista = motoristaService.getMotoristaByID(motoristaID);
        Stream<Viagem> viagens = viagemRepository.findByMotorista(motorista.getId()).stream();
        return viagens;
    }

    public Stream<Viagem> getViagemByEmpresa(Long empresaID){
        Empresa empresa = empresaService.getEmpresaByID(empresaID);
        Stream<Viagem> viagens = viagemRepository.findByEmpresa(empresa.getId()).stream();
        return viagens;
    }

}
