package com.qaCart.testCases.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor // ينشئ Constructor يحتوي على كل الحقول
@NoArgsConstructor  // ينشئ Constructor بدون أي معاملات
@JsonIgnoreProperties(ignoreUnknown = true)
public class loginPojo {
    private String email;
    private String password;
}
