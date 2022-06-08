package com.OPT.OPEasy.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OPT.OPEasy.DTO.TransporteDTO;
import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Mercado;
import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.model.Viagem;
import com.OPT.OPEasy.repository.TransporteRepository;

@Service
public class TransporteService {
    
    @Autowired
    private TransporteRepository transporteRepository;
    @Autowired
    private ViagemService viagemService;
    @Autowired
    private MercadoService mercadoService;

    public Transporte createTransporte(TransporteDTO transporte) throws Exception{
        if(hasTransporte(transporte.getTransporte()))
            throw new Exception("Transporte já cadastrado");

        Transporte newTransporte = new Transporte();
        newTransporte.SetTransporte(transporte);

        Viagem viagem = viagemService.getViagemById(transporte.getViagemID());
        newTransporte.setViagem(viagem);

        Mercado mercado = mercadoService.getMercadoByID(transporte.getMercadoID());
        newTransporte.setMercado(mercado);

        transporteRepository.save(newTransporte);
        return newTransporte;
    }

    public Transporte atualizarTransporte(TransporteDTO transporte, Long id) throws Exception{
        checkAtualizarTransporte(transporte, id);
        Transporte transporteFound = findById(id);
        transporteFound.SetTransporte(transporte);

        Mercado mercado = null;
        if(transporte.getMercadoID() != null)
            mercado = mercadoService.getMercadoByID(transporte.getMercadoID());
        
        Viagem viagem = null;
        if(transporte.getViagemID() != null)
            viagem = viagemService.getViagemById(transporte.getMercadoID());
        transporteFound.setViagem(viagem);
        transporteFound.setMercado(mercado);  
        transporteRepository.save(transporteFound);
        return transporteFound;
    }

    public Stream<Transporte> findAll(){
        Stream<Transporte> transportes = transporteRepository.findAll().stream();
        return transportes; 
    }

    public List<Transporte> listAll(){
        return transporteRepository.findAll();
    }

    public Transporte findById(Long id) throws Exception{
        Transporte opTransporte = transporteRepository.findById(id).orElseThrow(
            () -> new Exception("Transporte não encontrado."));
        return opTransporte;
    }

    public Stream<Transporte> findByMercado(Long id) throws Exception{
        Mercado mercado = mercadoService.getMercadoByID(id);
        Stream<Transporte> transporte = transporteRepository.findByMercado(mercado).stream();
        return transporte;
    }

    public Stream<Transporte> findByViagem(Long id) throws Exception{
        Viagem viagem = viagemService.getViagemById(id);
        Stream<Transporte> transporte = transporteRepository.findByViagem(viagem).stream();
        return transporte;
    }

    public Transporte deletarTransporte(Long id) throws Exception{
        Transporte transporte = findById(id);
        transporteRepository.delete(transporte);
        return transporte;

    }

    public boolean hasTransporte(Long id){
        Optional<Transporte> transporte = transporteRepository.findById(id);
        return transporte.isPresent();
    }

    private void checkAtualizarTransporte(TransporteDTO transporte, Long transporteId) throws Exception, ResourceNotFoundException {
        boolean mercadoFound = mercadoService.hasMercadoById(transporte.getMercadoID());
        if(!mercadoFound)
            throw new ResourceNotFoundException("Mercado não encontrado.");

    }

}
