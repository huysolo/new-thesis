package hcmut.thesis.backend.specifications;

import hcmut.thesis.backend.models.Topic;
import org.springframework.data.jpa.domain.Specification;

public class TopicSpecification {
    public static Specification<Topic> isProf(Integer idProf) {
        return (Specification<Topic>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("idProf"), idProf);
    }

    public static Specification<Topic> hasSem(Integer semNo) {
        return (Specification<Topic>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("semesterNo"), semNo);
    }

    public static Specification<Topic> isNotDraft() {
        return (Specification<Topic>)  (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get("semesterNo"));
    }

    public static Specification<Topic> isDraft() {
        return (Specification<Topic>)  (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get("semesterNo"));
    }
}
