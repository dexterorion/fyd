import { ResponseApi } from './../../model/response-api';
import { Router } from '@angular/router';
import { FuncaoExercicioService } from './../../service/funcao-exercicio.service';
import { DialogService } from './../../dialog.service';
import { SharedService } from './../../service/shared.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-funcao-exercicio-list',
  templateUrl: './funcao-exercicio-list.component.html',
  styleUrls: ['./funcao-exercicio-list.component.css']
})
export class FuncaoExercicioListComponent implements OnInit {
  page: number = 0;
  count: number = 5;
  pages:Array<number>;
  shared: SharedService;
  message: {};
  classCss: {};
  listFuncoes=[];

  constructor(
    private dialogService: DialogService,
    private funcaoExercicioService: FuncaoExercicioService,
    private router: Router
  ) { 
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.findAll(this.page, this.count);
  }

  findAll(page:number, count:number){
    this.funcaoExercicioService.findAll(page, count).subscribe((responseApi:ResponseApi) => {
      this.listFuncoes = responseApi['data']['content'];
      this.pages = new Array(responseApi['data']['totalPages']);
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  edit(id:string){
    this.router.navigate(['/funcao-exercicio/new', id]);
  }

  delete(id:string){
    this.dialogService.confirm('Você quer remover a função exercício?')
      .then((candelete:boolean) =>{
          if(candelete){
            this.message = {};
            this.funcaoExercicioService.delete(id).subscribe((responseApi:ResponseApi) =>{
              this.showMessage({
                type: 'success',
                text: 'Removido!'
              });
              this.findAll(this.page, this.count);
            }, err => {
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
            });
          }
      });
  }

  setNextPage(event:any){
    event.preventDefault();
    if(this.page+1 < this.pages.length){
      this.page = this.page +1;
      this.findAll(this.page, this.count);
    }
  }

  setPreviousPage(event:any){
    event.preventDefault();
    if(this.page > 0){
      this.page = this.page - 1;
      this.findAll(this.page, this.count);
    }
  }

  setPage(i, event:any){
    event.preventDefault();
    this.page = i;
    this.findAll(this.page, this.count);
  }

  private showMessage(message: {type: string, text:string}) : void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() =>{
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      'alert' : true
    }
    this.classCss['alert-'+type] = true;
  }

}
