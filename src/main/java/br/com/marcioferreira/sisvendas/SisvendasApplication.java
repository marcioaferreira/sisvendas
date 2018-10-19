package br.com.marcioferreira.sisvendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;
import br.com.marcioferreira.sisvendas.repositories.GrupoProdutoRepository;

@SpringBootApplication
public class SisvendasApplication implements CommandLineRunner {
	
	@Autowired
	private GrupoProdutoRepository grupoProdutoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SisvendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		GrupoProduto g1 = new GrupoProduto(null, "SSD");
		GrupoProduto g2 = new GrupoProduto(null, "Still");
		GrupoProduto g3 = new GrupoProduto(null, "Cerveja");
		
		grupoProdutoRepository.saveAll(Arrays.asList(g1, g2, g3));
	}
}
