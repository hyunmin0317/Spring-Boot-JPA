package jpabook.jpashop.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jpabook.jpashop.domain.QMember.member;
import static jpabook.jpashop.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQuerydslRepository {
    private final JPAQueryFactory query;

    public List<Order> findAll(OrderSearch orderSearch) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null)
            return null;
        return order.status.eq(statusCond);
    }

    private BooleanExpression nameLike(OrderSearch orderSearch) {
        if (!StringUtils.hasText(orderSearch.getMemberName()))
            return null;
        return member.name.contains(orderSearch.getMemberName());
    }
}
