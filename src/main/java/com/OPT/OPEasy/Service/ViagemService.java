package com.OPT.OPEasy.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.OPT.OPEasy.DTO.ViagemDTO;
import com.OPT.OPEasy.DTO.ViagemRelatorioDTO;
import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.Util.ViagemWritter;
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
    @Autowired
    private ViagemWritter viagemWritter;

    
    public Viagem cadastrarViagem(ViagemDTO viagem){
        checkCadastroViagem(viagem);
        Motorista motorista = motoristaService.getMotoristaByID(viagem.getMotoristaID());
        Empresa empresa = null;
        if(viagem.getEmpresaID() != null){
            empresa = empresaService.getEmpresaByID(viagem.getEmpresaID());
        }
            
        Viagem newViagem = new Viagem();
        newViagem.setAttributes(viagem);
        newViagem.setMotorista(motorista);
        newViagem.setEmpresa(empresa);
        viagemRepository.save(newViagem);
        return newViagem;
    }
    
    public Viagem updateViagem(Long id,ViagemDTO viagem){
        Viagem newViagem =  viagemRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Viagem não encontrada"));

        Motorista motorista = null;
        if(viagem.getMotoristaID() != null)
            motoristaService.getMotoristaByID(viagem.getMotoristaID());
        
        Empresa empresa = null;
        if(viagem.getEmpresaID() != null)
            empresa = empresaService.getEmpresaByID(viagem.getEmpresaID());
        
            
        newViagem.setAttributes(viagem);
        newViagem.setMotorista(motorista);
        newViagem.setEmpresa(empresa);

        viagemRepository.save(newViagem);
        return newViagem;
    }

    public Stream<Viagem> gerarRelatorio(ViagemRelatorioDTO rel) throws FileNotFoundException, IOException{
        List<Viagem> viagens = viagemRepository.findAll();
        viagemWritter.SetValues(rel);
        viagemWritter.createReport(viagens);
        return viagens.stream();
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
        Stream<Viagem> viagens = viagemRepository.findByMotorista(motorista).stream();
        return viagens;
    }

    public Stream<Viagem> getViagemByEmpresa(Long empresaID){
        Empresa empresa = empresaService.getEmpresaByID(empresaID);
        Stream<Viagem> viagens = viagemRepository.findByEmpresa(empresa).stream();
        return viagens;
    }

    public void checkCadastroViagem(ViagemDTO viagem){
        if(!motoristaService.hasMotoristaById(viagem.getMotoristaID()))
            throw new ResourceNotFoundException("Motorista não encontrado");
        if(viagem.getEmpresaID() != null && !empresaService.hasEmpresaById(viagem.getEmpresaID()))
            throw new ResourceNotFoundException("Empresa não encontrada");
    }
}
 