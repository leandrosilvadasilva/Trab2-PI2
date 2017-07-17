import { ClienteService } from './../cliente.service';
import { Component, OnInit } from '@angular/core';
import { Cliente} from "app/cliente";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-form-cliente',
  templateUrl: './form-cliente.component.html',
  styleUrls: ['./form-cliente.component.css']
})
export class FormClienteComponent implements OnInit {

   titulo:string;
  cliente: Cliente;
  idCliente:number;
  erro: string;

  constructor(private servico:ClienteService,
    private router: Router,
    private rota: ActivatedRoute) { }

  ngOnInit() { this.idCliente = this.rota.snapshot.params['id'];

    if(isNaN(this.idCliente)){
      this.cliente = new Cliente();    
    }
    else{
      this.servico.getCliente(this.idCliente).subscribe(
        data => this.cliente = data,
        error => this.erro = error
      )
    }    
  }

  salvar(){
    if(isNaN(this.idCliente)){
      console.log(this.cliente);
      this.servico.addCliente(this.cliente).
        subscribe(
          usuario => this.router.navigate(['/listaClientes']),
          error => this.erro = error
        );
    }

      //this.usuario = new Usuario();    
   // }
    else{
      this.servico.atualizarCliente(this.idCliente,this.cliente)
      .subscribe(
        data => this.router.navigate(['/listaClientes']),
        error => this.erro = error
      );
    }
    
        
  }

}
