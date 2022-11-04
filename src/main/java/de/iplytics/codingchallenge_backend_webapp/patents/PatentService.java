package de.iplytics.codingchallenge_backend_webapp.patents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatentService {

    private PatentRepository patentRepository;
    @Autowired
    public PatentService(PatentRepository patentRepository){
        this.patentRepository = patentRepository;
    }

    public Patent getSinglePatent(String publicationNumber){
        return patentRepository.findById(publicationNumber)
                .orElseThrow(
                        () -> new IllegalArgumentException("Cannot find patent ID " + publicationNumber)
                );
    }

    public List<Patent> searchPatentByTitle(String title){
        return new ArrayList<>();
    }
}
