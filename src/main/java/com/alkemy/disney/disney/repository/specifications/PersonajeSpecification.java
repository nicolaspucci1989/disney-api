package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {
  public static Specification<PersonajeEntity> getByFilters(PersonajeFilterDTO filterDTO) {

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

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
