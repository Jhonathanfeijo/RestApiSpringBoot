package br.com.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.exception.ProdutoNaoEncontradoException;
import br.com.domain.model.Produto;
import br.com.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtos;

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto saveProduto(@RequestBody @Valid Produto produto) {
		produtos.save(produto);
		return produto;
	}

	@GetMapping("/busca/{id}")
	public Produto findProdutoById(@PathVariable("id") Integer id) {
		return produtos.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException());
	}

	@GetMapping("/all")
	public List<Produto> allProdutos(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return produtos.findAll(example);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProdutoById(@PathVariable("id") Integer id) {
		produtos.findById(id).map(produto -> {
			produtos.deleteById(id);
			return produto;
		}).orElseThrow(() -> new ProdutoNaoEncontradoException());
	}
	
	@PutMapping("update/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProdutoById(@PathVariable("id") Integer id, @RequestBody @Valid Produto produto) {
		produtos.findById(id).map( produtoEncontrado -> {
			produto.setId(produtoEncontrado.getId());
			produtos.save(produto);
			return produto;
		}).orElseThrow(()-> new ProdutoNaoEncontradoException());
	}

}
