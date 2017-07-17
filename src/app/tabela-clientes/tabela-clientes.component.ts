import { Component, OnInit } from '@angular/core';
import { Cliente } from "app/cliente";
import { ClienteService } from "app/cliente.service";
import { ActivatedRoute, Router } from "@angular/router";


@Component({
  selector: 'app-tabela-clientes',
  templateUrl: './tabela-clientes.component.html',
  styleUrls: ['./tabela-clientes.component.css']
})
export class TabelaClientesComponent implements OnInit {
clientes: Cliente[];
  erro: string;

  constructor(private servico: ClienteService){}    
  
  ngOnInit() {
    
    this.carregarLista();
  }

  carregarLista(){
      this.servico.getClientes().subscribe(
        data => this.clientes = data,
        error => this.erro = error
      );

  }


  excluirCliente(cliente:Cliente){
    this.servico.excluirCliente(cliente).subscribe(
        data=> this.carregarLista(),
        error => this.erro = error
    );
  }

}
