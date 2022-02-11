package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
  public static Specification<Movie> getByFilters(MovieFilterDTO filterDTO) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasLength(filterDTO.getName())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + filterDTO.getName().toLowerCase() + "%"
            )
        );
      }

      if (filterDTO.getIdGenre() != null) {
        predicates.add(
            criteriaBuilder.equal(root.<Long>get("genre"), filterDTO.getIdGenre())
        );
      }

      criteriaQuery.distinct(true);

      String orderByField = "creationDate";
      criteriaQuery.orderBy(
          filterDTO.isAsc() ?
              criteriaBuilder.asc(root.get(orderByField)) :
              criteriaBuilder.desc(root.get(orderByField))
      );

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
