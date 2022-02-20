package com.alkemy.disney.disney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Smoke test")
@SpringBootTest
class SmokeTest {

  @Test
  @DisplayName("should pass")
  void smokeTest() {
    Assertions.assertTrue(true);
  }

}
