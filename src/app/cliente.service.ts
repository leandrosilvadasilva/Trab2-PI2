import { Injectable } from '@angular/core';


import { Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/RX'
import { Cliente } from "app/cliente";




import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class ClienteService {


  clientes: Cliente[] =[];
  urlResource = "http://localhost:8080/CrudUsuarios/api/clientes";

  constructor(private http: Http) { 
    
  }

  
  getClientes() : Observable<Cliente[]>{
    return this.http.get(this.urlResource)
      .map((res:Response)=>res.json())
      .catch((error:any)=>Observable.throw(error));

  }

  addCliente(cliente):Observable<Cliente>{
    let bodyString = JSON.stringify(cliente);
    console.log(bodyString);
    let headers = new Headers({'Content-Type':'application/json'})
    let options = new RequestOptions({headers:headers});
    return this.http.post(this.urlResource,
        bodyString, options)
        .map((res:Response)=>{})
        .catch((error:any)=>Observable.throw(error));
    
  }

  excluirCliente(cliente:Cliente):Observable<Cliente>{
    let url = this.urlResource+"/"+cliente.id;
    return this.http.delete(url)        
      .map((res:Response)=>{})
      .catch((error:any)=>Observable.throw(error));


    
  }

  getCliente(id):Observable<Cliente>{
    let url = this.urlResource+"/"+id;
    return this.http.get(url)        
      .map((res:Response)=>res.json())
      .catch((error:any)=>Observable.throw(error));
  }

  atualizarCliente(id:number, cliente:Cliente):Observable<Cliente>{
    let url = this.urlResource+"/"+id;
    let bodyString = JSON.stringify(cliente);
    console.log(bodyString);
    let headers = new Headers({'Content-Type':'application/json'})
    let options = new RequestOptions({headers:headers});
    return this.http.put(url,
        bodyString, options)
        .map((res:Response)=>{})
        .catch((error:any)=>Observable.throw(error));
  }
}