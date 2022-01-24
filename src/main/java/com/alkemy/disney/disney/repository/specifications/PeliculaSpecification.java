package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {
  public static Specification<Pelicula> getByFilters(PeliculaFilterDTO filterDTO) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasLength(filterDTO.getName())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("titulo")),
                "%" + filterDTO.getName().toLowerCase() + "%"
            )
        );
      }

      if (filterDTO.getIdGenre() != null) {
        predicates.add(
            criteriaBuilder.equal(root.<Long>get("genero"), filterDTO.getIdGenre())
        );
      }

      criteriaQuery.distinct(true);

      String orderByField = "fechaDeCreacion";
      criteriaQuery.orderBy(
          filterDTO.isAsc() ?
              criteriaBuilder.asc(root.get(orderByField)) :
              criteriaBuilder.desc(root.get(orderByField))
      );

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
