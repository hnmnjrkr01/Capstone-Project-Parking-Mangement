package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.PaymentMethod;
import com.carprakingapp.webapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodDAO extends JpaRepository<PaymentMethod, Long> {

    PaymentMethod findByPaymentId(Integer paymentId);

    User findUserByUserId(Integer userId);

    @Query("select pm from PaymentMethod pm where pm.paymentMethod = :paymentMethod")
    List<PaymentMethod> findByPayMethod(String paymentMethod);

}
