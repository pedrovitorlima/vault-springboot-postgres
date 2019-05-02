package br.pedro.springboot.springvault;

import br.pedro.springboot.springvault.domain.Secrets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringVaultApplication {

	@Autowired
	private Secrets secrets;

	public static void main(String[] args) {
		SpringApplication.run(SpringVaultApplication.class, args);
	}

	@PostConstruct
	private void postConstruct() {
		System.out.println("GIT User: " + secrets.getGitUser());
		System.out.println("GIT Pass: " + secrets.getGitPass());
	}
}
