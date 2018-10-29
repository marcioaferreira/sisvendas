package br.com.marcioferreira.sisvendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.marcioferreira.sisvendas.domain.CorProduto;
import br.com.marcioferreira.sisvendas.domain.FamiliaProduto;
import br.com.marcioferreira.sisvendas.domain.GrupoProduto;
import br.com.marcioferreira.sisvendas.domain.Produto;
import br.com.marcioferreira.sisvendas.domain.TamanhoProduto;
import br.com.marcioferreira.sisvendas.repositories.CorProdutoRepository;
import br.com.marcioferreira.sisvendas.repositories.FamiliaProdutoRepository;
import br.com.marcioferreira.sisvendas.repositories.GrupoProdutoRepository;
import br.com.marcioferreira.sisvendas.repositories.ProdutoRepository;
import br.com.marcioferreira.sisvendas.repositories.TamanhoProdutoRepository;

@SpringBootApplication
public class SisvendasApplication implements CommandLineRunner {
	
	@Autowired
	private GrupoProdutoRepository grupoProdutoRepository;
	
	@Autowired
	private FamiliaProdutoRepository familiaProdutoRepository;
	
	@Autowired
	private TamanhoProdutoRepository tamanhoProdutoRepository;
	
	@Autowired
	private CorProdutoRepository corProdutoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SisvendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		GrupoProduto g1 = new GrupoProduto(null, "SSD");
		GrupoProduto g2 = new GrupoProduto(null, "Still");
		GrupoProduto g3 = new GrupoProduto(null, "Cerveja");
		
		FamiliaProduto f1 = new FamiliaProduto(null, "Consumo Imediato");
		FamiliaProduto f2 = new FamiliaProduto(null, "Consumo Futuro");
		
		TamanhoProduto t1 = new TamanhoProduto(null, "Pet 2L");
		TamanhoProduto t2 = new TamanhoProduto(null, "KS");
		
		CorProduto c1 = new CorProduto(null, "Geral");
		
		
		Produto p1 = new Produto(null, "0123456789XXX", "Coca Cola Pet 2L", 5.47, 4.82, 1000.0, 500.0, 5000.0, g1, f2, t1, c1);
		Produto p2 = new Produto(null, "0123456d89XXX", "Fanta Laranja Pet 2L", 4.65, 3.98, 700.0, 300.0, 1000.0, g1, f2, t1, c1);
		Produto p3 = new Produto(null, "01234567s9XXX", "Sprite Pet 2L", 4.65, 3.98, 300.0, 100.0, 700.0, g1, f2, t1, c1);
		Produto p4 = new Produto(null, "012345678qXXX", "Coca Cola KS 290ml", 0.95, 1.50, 800.0, 100.0, 500.0, g1, f1, t2, c1);
		
		g1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4));
		
		f1.getProdutos().addAll(Arrays.asList(p4));
		f2.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		t1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		t2.getProdutos().addAll(Arrays.asList(p4));
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4));
		
		grupoProdutoRepository.saveAll(Arrays.asList(g1, g2, g3));
		familiaProdutoRepository.saveAll(Arrays.asList(f1, f2));
		tamanhoProdutoRepository.saveAll(Arrays.asList(t1, t2));
		corProdutoRepository.saveAll(Arrays.asList(c1));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
	}
}
