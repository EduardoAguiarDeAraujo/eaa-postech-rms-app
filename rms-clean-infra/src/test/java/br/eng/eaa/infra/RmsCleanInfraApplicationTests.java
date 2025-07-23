package br.eng.eaa.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class RmsCleanInfraApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	@DisplayName("Verifica se o contexto da aplicação Spring Boot é carregado com sucesso")
	void contextLoads() {
		assertThat(context).isNotNull();
	}
	@Test
	@DisplayName("Testa o método main para garantir que ele não lança exceções inesperadas")
	void mainMethodRunsWithoutErrors() {
		assertDoesNotThrow(() -> {
			RmsCleanInfraApplication.main(new String[]{});
		}, "O método main deve ser executado sem lançar exceções");
	}
}
