package com.OPT.OPEasy.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Universo;
import com.OPT.OPEasy.repository.UniversoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversoService {

    @Autowired
    UniversoRepository universoRepository;

    @Autowired
    MercadoService mercadoService;

    SimpleDateFormat formatter;

    public Universo cadastrarUniverso(Universo universo) throws Exception{
        checkCadastroUniverso(universo);
        Universo newUniverso = new Universo();
        newUniverso.setAttributes(universo);
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

    
    
    public Universo deleteUniverso(Universo universo){
        universoRepository.delete(universo);

        return null;
    }

    public Stream<Universo> findAll(){
        return universoRepository.findAll().stream();
    }

    public Stream<Universo> findByIdMercado(Long id){
        return universoRepository.findByidMercado(id).stream();
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

    public boolean hasUniversoByOrder(String order){
        return universoRepository.findByOrdem(order).isPresent();
    }


    private void checkCadastroUniverso(Universo universo) throws Exception{
        if(universo.getOrdem() == null)
            throw new Exception("A ordem não pode ser nula");

        if(hasUniversoByOrder(universo.getOrdem()))
            throw new Exception("Ordem informada já se encontra cadastrado. Tente outro."); 
        
        if(universo.getIdMercado() == null)
            throw new Exception("O id do mercado não pode ser nulo.");

        if(!mercadoService.hasMercadoById(universo.getIdMercado()))
            throw new ResourceNotFoundException("O mercado não foi encontrado.");
        
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


        //Caso seja passado um mercado é validado se o id é encontrado. 
        //Caso o mercado não seja encontrado, é lançada uma exceção.
        if(universo.getIdMercado() != null && !mercadoService.hasMercadoById(universo.getIdMercado()))
            throw new Exception("O mercado não foi encontrado.");

        // if(universo.getData() != null && universo.getData() != universoFound.getData()){
        //     formatter = new SimpleDateFormat();
        //     try{
        //         Date date = formatter.parse(universo.getData())
        //     }
        //}
        

    }
}
