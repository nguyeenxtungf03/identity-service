package com.example.identityservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdentityServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        String a ="$2a$10$B6pecHk2ZplSEZ9c8aSMnOlCpvHc.3gdZnIkLA1YbBZY0lbwojjNm";
        System.out.println(a.length());
    }

}
