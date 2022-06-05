package com.OPT.OPEasy.Service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OPT.OPEasy.DTO.TransporteDTO;
import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Mercado;
import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.repository.TransporteRepository;

@Service
public class TransporteService {
    
    @Autowired
    private TransporteRepository transporteRepository;
    @Autowired
    private MercadoService mercadoService;

    public Transporte createTransporte(TransporteDTO transporte) throws Exception{
        if(hasTransporte(transporte.getTransporte()))
            throw new Exception("Transporte já cadastrado");
        Transporte newTransporte = new Transporte();
        newTransporte.SetTransporte(transporte);
        transporteRepository.save(newTransporte);
        return newTransporte;
    }

    public Transporte atualizarTransporte(TransporteDTO transporte, Long id) throws Exception{
        checkAtualizarTransporte(transporte, id);
        Transporte transporteFound = findById(id);
        transporteFound.SetTransporte(transporte);

        Mercado mercado = null;
        if(transporte.getMercadoId() != null)
            mercado = mercadoService.getMercadoByID(transporte.getMercadoId());
        transporteFound.setMercado(mercado);  
        transporteRepository.save(transporteFound);
        return transporteFound;
    }

    public Stream<Transporte> findAll(){
        Stream<Transporte> transportes = transporteRepository.findAll().stream();
        return transportes; 
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
        boolean mercadoFound = mercadoService.hasMercadoById(transporte.getMercadoId());
        if(!mercadoFound){
            throw new ResourceNotFoundException("Mercado não encontrado.");
        }


    }

}
