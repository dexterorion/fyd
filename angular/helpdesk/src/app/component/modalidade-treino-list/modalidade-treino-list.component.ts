import { ResponseApi } from './../../model/response-api';
import { Router } from '@angular/router';
import { DialogService } from './../../dialog.service';
import { SharedService } from './../../service/shared.service';
import { Component, OnInit } from '@angular/core';
import { ModalidadeTreinoService } from '../../service/modalidade-treino.service';

@Component({
  selector: 'app-modalidade-treino-list',
  templateUrl: './modalidade-treino-list.component.html',
  styleUrls: ['./modalidade-treino-list.component.css']
})
export class ModalidadeTreinoListComponent implements OnInit {
  page: number = 0;
  count: number = 5;
  pages:Array<number>;
  shared: SharedService;
  message: {};
  classCss: {};
  lista=[];

  constructor(
    private dialogService: DialogService,
    private modalidadeTreinoService: ModalidadeTreinoService,
    private router: Router
  ) { 
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.findAll(this.page, this.count);
  }

  findAll(page:number, count:number){
    this.modalidadeTreinoService.findAll(page, count).subscribe((responseApi:ResponseApi) => {
      this.lista = responseApi['data']['content'];
      this.pages = new Array(responseApi['data']['totalPages']);
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  edit(id:string){
    this.router.navigate(['/modalidade-treino/new', id]);
  }

  delete(id:string){
    this.dialogService.confirm('Você quer remover a modalidade de treino?')
      .then((candelete:boolean) =>{
          if(candelete){
            this.message = {};
            this.modalidadeTreinoService.delete(id).subscribe((responseApi:ResponseApi) =>{
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
