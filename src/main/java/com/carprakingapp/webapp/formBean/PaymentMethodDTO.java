package com.carprakingapp.webapp.formBean;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PaymentMethodDTO {
    private Integer userId;

    private Character paymentMethod;

    private LocalDateTime dateOfPayment;

}
