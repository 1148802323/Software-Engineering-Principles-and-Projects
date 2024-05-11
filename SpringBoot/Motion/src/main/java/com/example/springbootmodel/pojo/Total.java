package com.example.springbootmodel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Total {
    private int totalUsers;
    private int vipUsers;
    private int normalUsers;
}