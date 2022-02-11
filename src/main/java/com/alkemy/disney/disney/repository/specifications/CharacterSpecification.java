package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.entity.Character;
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
public class CharacterSpecification {
  public static Specification<Character> getByFilters(CharacterFilterDTO filterDTO) {

    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasLength(filterDTO.getName())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + filterDTO.getName().toLowerCase() + "%"
            )
        );
      }

      if (filterDTO.getAge() != null) {
        predicates.add(
            criteriaBuilder.equal(root.<Integer>get("age"), filterDTO.getAge())
        );
      }

      if (!CollectionUtils.isEmpty(filterDTO.getIdMovies())) {
        Join<Movie, Character> join = root.join("movies", JoinType.INNER);
        Expression<String> moviesId = join.get("id");
        predicates.add(moviesId.in(filterDTO.getIdMovies()));
      }

      criteriaQuery.distinct(true);

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
