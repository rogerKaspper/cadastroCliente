package com.cadastro.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cadastro.cadastro.model.Cidade;
import com.cadastro.cadastro.model.Cliente;
import com.cadastro.cadastro.repository.CidadeRepository;
import com.cadastro.cadastro.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private CidadeRepository ir;
	
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.GET)
	public String form(){
		return "cadastrarCliente";
	}
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.POST)
    public String form(Cliente cliente) {
		cr.save(cliente);
        return "redirect:http://localhost:8080/cadastrarCliente";
    }
	
	@RequestMapping("/clientes")
    public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("clientesPage");
		Iterable<Cliente> cliente = cr.findAll();
		mv.addObject("cliente", cliente);	
        return mv;
    }
	
	@RequestMapping(value="/detalheCliente/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalheCliente(@PathVariable("codigo") long codigo){
		Cliente cliente = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("detalheCliente");
		mv.addObject("cliente", cliente);
		
		Cidade cidade = ir.findByCliente(cliente);
		mv.addObject("cidade", cidade);
		return mv;
	}
	
	@RequestMapping(value="/detalheCliente/{codigo}", method=RequestMethod.POST)
    public String detalhesClientePost(@PathVariable("codigo") long codigo, Cidade cidade) {
		Cliente cliente = cr.findByCodigo(codigo);
		cidade.setCliente(cliente);
		ir.save(cidade);
        return "clientesPage";
    }

	
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long codigo){
		Cliente cliente = cr.findByCodigo(codigo);
		cr.delete(cliente);
		return "redirect:/clientes";
	}
	
	@RequestMapping(value="/PesquisaClienteNome", method=RequestMethod.GET)
	public String pesquisarClienteNome(){	
		return "PesquisaClienteNome";		
	}
	
	@RequestMapping(value="/PesquisaClienteNome", method=RequestMethod.POST)
	public ModelAndView pesquisarClientePorNome(String nomeCompleto){	
		Cliente cliente = cr.findByNomeCompleto(nomeCompleto);
		ModelAndView mv = new ModelAndView("detalheCli");
		Cidade cidade = ir.findByCliente(cliente);
		mv.addObject("cliente", cliente);
		mv.addObject("cidade", cidade);
		return mv;		
	}
	
	@RequestMapping(value="/PesquisaClienteCodigo", method=RequestMethod.GET)
	public String pesquisarClientePorCodigo(){	
		return "PesquisaClienteCodigo";		
	}
	
	@RequestMapping(value="/PesquisaClienteCodigo", method=RequestMethod.POST)
	public ModelAndView pesquisarClientePorCodigo(long codigo){	
		Cliente cliente = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("detalheCli");
		Cidade cidade = ir.findByCliente(cliente);
		mv.addObject("cliente", cliente);
		mv.addObject("cidade", cidade);
		return mv;		
	}
	
	@RequestMapping(value="/PesquisaCidadeNome", method=RequestMethod.GET)
	public String pesquisaCidadeNome(){	
		return "PesquisaCidadeNome";		
	}
	
	@RequestMapping(value="/PesquisaCidadeNome", method=RequestMethod.POST)
	public ModelAndView pesquisaCidadeNome(String nome){	
		ModelAndView mv = new ModelAndView("detalheCid");
		Cidade cidade = ir.findByNome(nome);;
		mv.addObject("cidade", cidade);
		return mv;		
	}
	
	@RequestMapping(value="/PesquisaCidadeCodigo", method=RequestMethod.GET)
	public String pesquisaCidadeCodigo(){	
		return "PesquisaCidadeCodigo";		
	}
	
	@RequestMapping(value="/PesquisaCidadeCodigo", method=RequestMethod.POST)
	public ModelAndView pesquisaCidadeCodigo(Long codigo){	
		ModelAndView mv = new ModelAndView("detalheCid");
		Cidade cidade = ir.findByCodigo(codigo);;
		mv.addObject("cidade", cidade);
		return mv;		
	}

}

