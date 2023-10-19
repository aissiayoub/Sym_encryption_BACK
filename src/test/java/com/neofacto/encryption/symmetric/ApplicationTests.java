package com.neofacto.encryption.symmetric;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainMethodTest() throws InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			try {
				Application.main(new String[]{});
			} catch (Exception e) {
				fail("Application failed to start: " + e.getMessage());
			}
		});

		executor.awaitTermination(5, TimeUnit.SECONDS);
		executor.shutdownNow();
	}
}
