package ufpr.marvel_app_spring.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufpr.marvel_app_spring.models.AluguelHq;
import ufpr.marvel_app_spring.repositories.AluguelHqRepository;

@Service
public class AluguelHqService {
	
	Logger logger = LoggerFactory.getLogger(AluguelHqService.class);

	@Autowired
	private AluguelHqRepository aluguelHqRepository;

	
	public AluguelHq save(AluguelHq aluguelHq) {
        return aluguelHqRepository.save(aluguelHq);
    }
	
	public LocalDate setDataDevolucao(String dataDevolucaoStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
            return dataDevolucao;
        } catch (Exception e) {
            logger.error("Erro: "+ e.getMessage());
        }
        return null;
    }
}
