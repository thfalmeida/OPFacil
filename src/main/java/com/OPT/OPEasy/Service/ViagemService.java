package com.OPT.OPEasy.Service;

import java.util.stream.Stream;

import com.OPT.OPEasy.DTO.TransporteDTO;
import com.OPT.OPEasy.DTO.ViagemDTO;
import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Empresa;
import com.OPT.OPEasy.model.Mercado;
import com.OPT.OPEasy.model.Motorista;
import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.model.Viagem;
import com.OPT.OPEasy.repository.TransporteRepository;
import com.OPT.OPEasy.repository.ViagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {
    
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private MercadoService mercadoService;

    @Autowired
    private ViagemRepository viagemRepository;
    @Autowired
    private TransporteRepository transporteRepository;

    
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

    // public Stream<Viagem> gerarRelatorio(ViagemRelatorioDTO rel) throws FileNotFoundException, IOException{
    //     List<Viagem> viagens = viagemRepository.findAll();
    //     viagemWritter.SetValues(rel);
    //     viagemWritter.createReport(viagens);
    //     return viagens.stream();
    // }

    public Viagem deleteViagem(Viagem viagem){
        Viagem viagemFound = getViagemById(viagem.getId());
        viagemRepository.delete(viagemFound);
        return viagemFound;
    }

    public Viagem addTransporte(Long id, TransporteDTO transporteDTO) throws Exception{
        Viagem viagem = getViagemById(id);

        Transporte transporte = new Transporte();
        boolean alreadyHasTransport = hasTransporte(transporteDTO.getTransporte());
        if(alreadyHasTransport){
            throw new Exception("Transporte já cadastrado");
        }

        Mercado mercado = mercadoService.getMercadoByID(transporteDTO.getMercadoID());
        transporte.setMercado(mercado);
        transporte.setTransporte(transporteDTO.getTransporte());
        transporteRepository.save(transporte);

        viagem.AddTransporte(transporte);
        viagemRepository.save(viagem);
        return viagem;
    }

    public Viagem deletarTransporte(Long id, TransporteDTO transporteDTO){
        Viagem viagem = getViagemById(id);
        Transporte transporte = viagem.GetTransporteByTransporteNo(transporteDTO.getTransporte());
        if(transporte == null)
            return viagem;

        viagem.DeleteTransporte(transporte);
        viagemRepository.save(viagem);
        transporteRepository.delete(transporte);
        return viagem;
    }


    // public Transporte getTransporteByTransporteNo(Long id){
    //     Viagem[] viagens = (Viagem[]) findAll().toArray();        
    //     for(Viagem v: viagens){
    //         Transporte transporte = v.GetTransporteByTransporteNo(id);
    //         if(v != null)
    //             return transporte;
    //     }

    //     return null;
    // }

    public boolean hasTransporte(Long transporte){
        return transporteRepository.findByTransporte(transporte).isPresent();       
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
 