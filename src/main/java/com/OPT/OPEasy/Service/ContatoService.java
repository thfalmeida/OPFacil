package com.OPT.OPEasy.Service;

import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Contato;
import com.OPT.OPEasy.model.Empresa;
import com.OPT.OPEasy.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    
    @Autowired
    ContatoRepository contatoRepository;
    @Autowired 
    EmpresaService empresaService;


    public Contato CadastrarContato(Contato contato) throws Exception{
        if(hasContatoByNick(contato.getNick()))
            throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");
        
        Empresa empresa = contato.getEmpresa();
        if(empresa != null){
            empresa = empresaService.getEmpresaByID(empresa.getId());
            contato.setEmpresa(empresa);
        }
        
        contatoRepository.save(contato);
        return contato;
    }

    public Contato updateContato(Long id,Contato contato) throws Exception{
        // Contato contatoFound = contatoRepository.findById(id).orElseThrow(
        //     () -> new ResourceNotFoundException(Error.ID_NOT_FOUND));
        // Contato contatoFoundByNick = getContatoByNick(contato.getNick());
        Contato contatoFound = checkContatoUpdate(id, contato);
        contatoFound.setAttributes(contato);
        contatoRepository.save(contatoFound);
        return contatoFound;
    }

    public Contato deleteContato(Contato contato){
        contatoRepository.findById(contato.getId()).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        contatoRepository.delete(contato);
        return contato;
    }

    public Stream<Contato> findAll(){
        return contatoRepository.findAll().stream();
    }

    public Contato getContatoById(Long id){
        Contato contato = contatoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        return contato;
    }

    public Contato getContatoByNick(String nick){
        Contato contato = contatoRepository.findByNick(nick).get();
        return contato;
    }

    public boolean hasContatoByNick(String nick){
        return contatoRepository.findByNick(nick).isPresent();
    }

    public boolean hasContatoById(Long id){
        return contatoRepository.findById(id).isPresent();
    }

    //Checa se o esta tentando alterar o nick do contato,
    //Se estiver, checa se existe outro contato com o nick, 
    //caso exista, retorna erro, no contrário, o contato é alterado 
    public Contato checkContatoUpdate(Long id, Contato contato) throws Exception{
        Contato contatoFound = contatoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

        String nick = contato.getNick();

        if(contato.getNick() == null || !hasContatoByNick(nick))
            return contatoFound;

        Contato contatoByNick = contatoRepository.findByNick(nick).get();
        if(contatoByNick.getId() == contatoFound.getId()){
            return contatoFound;
        } else{
            throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");
        }
        
    }

}

