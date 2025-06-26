package fr.fms.services;


import fr.fms.entities.Avis;
import fr.fms.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;

    public List<Avis> getAllAvis(){
        return avisRepository.findAll();
    }
    public Optional<Avis> getAvisById(Long id){
        return avisRepository.findById(id);
    }
    public Avis saveAvis(Avis avis){
        return avisRepository.save(avis);
    }
    public void deleteAvis(Long id){
        avisRepository.deleteById(id);
    }
}
