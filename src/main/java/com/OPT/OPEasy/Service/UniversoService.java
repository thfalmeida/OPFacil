package com.OPT.OPEasy.Service;

import java.text.SimpleDateFormat;

import java.util.stream.Stream;

import com.OPT.OPEasy.DTO.UniversoDTO;
import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.model.Universo;
import com.OPT.OPEasy.repository.UniversoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversoService {

    @Autowired
    UniversoRepository universoRepository;

    @Autowired
    TransporteService transporteService;

    SimpleDateFormat formatter;

    public Universo cadastrarUniverso(UniversoDTO universo) throws Exception{
        checkCadastroUniverso(universo);
        Transporte transporte = transporteService.findById(universo.getTransporteID());
        Universo newUniverso = new Universo();
        newUniverso.setAttributes(universo);
        newUniverso.setTransporte(transporte);
        universoRepository.save(newUniverso);
        return newUniverso;
    }

    public Universo atualizarUniverso(Universo universo) throws Exception{
        Universo universoFound = getUniversoById(universo.getId());
        checkAtualizarUniverso(universo, universoFound);
        universoFound.setAttributes(universo);
        universoRepository.save(universoFound);
        return universoFound;

    }

    
    
    public Universo deleteUniverso(Long id) throws Exception{
        Universo universo = universoRepository.findById(id).orElseThrow(
            () -> new Exception("Universo não encontrado."));
        universoRepository.delete(universo);

        return null;
    }

    public Stream<Universo> findAll(){
        Stream<Universo> uni = universoRepository.findAll().stream();
        
        return uni;
    }

    public Universo getUniversoById(Long id){
        Universo universo = universoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

        return universo;
    }

    public Universo getUniversoByOrdem(String ordem){
        Universo universo = universoRepository.findByOrdem(ordem).orElseThrow(
            () -> new ResourceNotFoundException("A ordem informado não foi encontrado."));
        return universo;
    }

    public Stream<Universo> findByTransporte(Long transporteID) throws Exception{
        Transporte transporte = transporteService.findById(transporteID);
        Stream<Universo> uni = universoRepository.findByTransporte(transporte).stream();
        return uni;
    }

    public boolean hasUniversoByOrder(String order){
        return universoRepository.findByOrdem(order).isPresent();
    }


    private void checkCadastroUniverso(UniversoDTO universo) throws Exception{
        if(universo.getOrdem() == null)
            throw new Exception("A ordem não pode ser nula");

        if(hasUniversoByOrder(universo.getOrdem()))
            throw new Exception("Ordem informada já se encontra cadastrado. Tente outro."); 
        
        if(!transporteService.hasTransporte(universo.getTransporteID()))
            throw new Exception("O transporte informado não existe.");
        
    }

    private void checkAtualizarUniverso(Universo universo, Universo universoFound) throws Exception{
        /**
         * Se o usuario está passando o valor da ordem, é necessário que seja passado também
         * O id para se seja validado os proximos passos. Caso não passe, é retornado uma exceção.
         * 
         * Então, passado o ID é validado se a ordem inserida é igual à ordem da Universo cadastrado.
         * Caso seja igual, nada muda.
         * 
         * Caso seja diferente, é validado se a ordem passada já está cadastrada. Se não estiver, entaõ ela é alterada.
         * Caso contrario, é lançado uma exceção
         */

        if(universo.getOrdem() != null){
            if(universoFound.getOrdem() != universo.getOrdem() && hasUniversoByOrder(universo.getOrdem())){
                throw new Exception("Ordem já cadastrada.");
            }
        }

    }
}
