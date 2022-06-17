package br.com.labs.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Testcontainers
public class CategoryControllerTest {

	@Autowired
	private MockMvc mvc;

	@Container
	public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:11.1")
			.withDatabaseName("loja").withUsername("postgres").withPassword("postgres");

//	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//			TestPropertyValues
//					.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
//							"spring.datasource.username=" + postgreSQLContainer.getUsername(),
//							"spring.datasource.password=" + postgreSQLContainer.getPassword())
//					.applyTo(configurableApplicationContext.getEnvironment());
//		}
//	}

	@Before
	public void before() {
		postgreSQLContainer.start();
	}

	@After
	public void after() {
		postgreSQLContainer.stop();
	}

	@Test
	public void get_all_category_and_expect_code_200() throws Exception {
		mvc.perform(get("/category")).andExpect(status().isOk());
	}

	@Test
	public void create_category_and_failed_and_expect_code_400() throws Exception {

		Category category = new Category();

		mvc.perform(post("/category").content(asJsonString(category)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
