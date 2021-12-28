package ru.development.users.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.development.users.dto.UserFilterDto;
import ru.development.users.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final UserFilterDto filter;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null){
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
        }

        if (filter.getStatus() != null){
            predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
