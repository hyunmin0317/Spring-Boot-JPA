package jpabook.jpashop.repository.simplequery;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.dto.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<SimpleOrderDto> findOrderDtos() {
        return em.createQuery(
                        "select new jpabook.jpashop.dto.SimpleOrderDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", SimpleOrderDto.class)
                .getResultList();
    }
}
