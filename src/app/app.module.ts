import { ClienteService } from './cliente.service';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { TabelaUsuariosComponent } from './tabela-usuarios/tabela-usuarios.component';
import { FormUsuariosComponent } from './form-usuarios/form-usuarios.component';
import { UsuarioService } from "app/usuario.service";
import { FormClienteComponent } from './form-cliente/form-cliente.component';
import { TabelaClientesComponent } from './tabela-clientes/tabela-clientes.component';


const appRotas:Routes=[
  { path:'lista', component: TabelaUsuariosComponent },
  { path:'edicao/:ind', component:FormUsuariosComponent },
  { path:'novo', component:FormUsuariosComponent },
  { path:'novoCliente', component:FormClienteComponent},
  { path:'listaClientes', component: TabelaClientesComponent },
  
  
  { path: '', redirectTo:'inicio', pathMatch:'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    TabelaUsuariosComponent,
    FormUsuariosComponent,
    FormClienteComponent,
    TabelaClientesComponent,
    
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRotas),
    
  ],
  providers: [UsuarioService,
    ClienteService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
