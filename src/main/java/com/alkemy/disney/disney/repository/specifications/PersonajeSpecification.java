package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {
  public static Specification<Personaje> getByFilters(CharacterFilterDTO filterDTO) {

    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasLength(filterDTO.getName())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("nombre")),
                "%" + filterDTO.getName().toLowerCase() + "%"
            )
        );
      }

      if (filterDTO.getEdad() != null) {
        predicates.add(
            criteriaBuilder.equal(root.<Integer>get("edad"), filterDTO.getEdad())
        );
      }

      if (!CollectionUtils.isEmpty(filterDTO.getIdMovies())) {
        Join<Movie, Personaje> join = root.join("peliculas", JoinType.INNER);
        Expression<String> moviesId = join.get("id");
        predicates.add(moviesId.in(filterDTO.getIdMovies()));
      }

      criteriaQuery.distinct(true);

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
