package ufpr.marvel_app_spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufpr.marvel_app_spring.models.MarvelHq;
import ufpr.marvel_app_spring.repositories.MarvelHqRepository;

@Service
public class MarvelHqService {
	
	@Autowired
	private MarvelHqRepository marvelHqRepository;
	
	public MarvelHq findById(Long id) {
		return marvelHqRepository.findById(id).orElse(null);
	}
	
	public MarvelHq save(MarvelHq marvelHq) {
		Optional<MarvelHq> hq = marvelHqRepository.findById(marvelHq.getId());
		if(!hq.isPresent()) {
			return marvelHqRepository.save(marvelHq);
		} else if(hq.isPresent()){
			MarvelHq findHq = hq.get();
			return findHq;
		} else {
			return null;
		}
        
    }
}
