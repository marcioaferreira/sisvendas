package br.com.marcioferreira.sisvendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;
import br.com.marcioferreira.sisvendas.domain.Produto;
import br.com.marcioferreira.sisvendas.repositories.GrupoProdutoRepository;
import br.com.marcioferreira.sisvendas.repositories.ProdutoRepository;

@SpringBootApplication
public class SisvendasApplication implements CommandLineRunner {
	
	@Autowired
	private GrupoProdutoRepository grupoProdutoRepository;
	
	@Autowired
	private ProdutoRepository ProdutoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SisvendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		GrupoProduto g1 = new GrupoProduto(null, "SSD");
		GrupoProduto g2 = new GrupoProduto(null, "Still");
		GrupoProduto g3 = new GrupoProduto(null, "Cerveja");
		
		grupoProdutoRepository.saveAll(Arrays.asList(g1, g2, g3));
		
		Produto p1 = new Produto(null, "0123456789XXX", "Coca Cola Pet 2L", 5.47, 4.82, 1000.0, 500.0, 5000.0, g1);
		Produto p2 = new Produto(null, "0123456789XXX", "Fanta Laranja Pet 2L", 4.65, 3.98, 700.0, 300.0, 1000.0, g1);
		Produto p3 = new Produto(null, "0123456789XXX", "Fanta Laranja Pet 2L", 4.65, 3.98, 300.0, 100.0, 700.0, g1);
		
		g1.getProdutos().addAll(Arrays.asList(p1));
		g1.getProdutos().addAll(Arrays.asList(p2));
		g1.getProdutos().addAll(Arrays.asList(p3));
		
		ProdutoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
	}
}
