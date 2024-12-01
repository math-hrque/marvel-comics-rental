package ufpr.marvel_app_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ufpr.marvel_app_spring.models.MarvelHq;

public interface MarvelHqRepository extends JpaRepository<MarvelHq, Long> {

}
