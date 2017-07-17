import { Component, OnInit } from '@angular/core';
import { Usuario } from "app/usuario";
import { UsuarioService } from "app/usuario.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-tabela-usuarios',
  templateUrl: './tabela-usuarios.component.html',
  styleUrls: ['./tabela-usuarios.component.css']
})
export class TabelaUsuariosComponent implements OnInit {
 usuarios: Usuario[];
  erro: string;

  constructor(private servico: UsuarioService){}    
  
  ngOnInit() {
    
    this.carregarLista();
  }

  carregarLista(){
      this.servico.getUsuarios().subscribe(
        data => this.usuarios = data,
        error => this.erro = error
      );

  }


  excluirUsuario(usuario:Usuario){
    this.servico.excluirUsuario(usuario).subscribe(
        data=> this.carregarLista(),
        error => this.erro = error
    );
  }

}
