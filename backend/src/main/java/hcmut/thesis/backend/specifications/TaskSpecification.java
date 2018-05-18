package hcmut.thesis.backend.specifications;

import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.modelview.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class TaskSpecification implements Specification<Task>   {

    public TaskSpecification(SearchCriteria searchCriteria) {
        criteria = searchCriteria;
    }
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate
            (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

    public static Specification<Task> hasId(Integer topic) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("updateTime")));
            return criteriaBuilder.equal(root.get("idTopicSem"), topic);
        };
    }

    public static Specification<Task> likeTitle(String title) {
        return (Specification <Task>) (rt, crt, crtB) -> (crtB.like(rt.get("title"), "%" + title + "%"));
    }

    public static Specification<Task> isSubmitted() {
        return (Specification<Task>) (rt, crt, crtB) -> (crtB.equal(rt.get("submit"), 1));
    }

}
