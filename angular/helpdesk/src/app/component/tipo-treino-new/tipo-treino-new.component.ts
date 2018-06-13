import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { TipoTreinoService } from './../../service/tipo-treino.service';
import { SharedService } from './../../service/shared.service';
import { TipoTreino } from './../../model/tipo-treino.model';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-tipo-treino-new',
  templateUrl: './tipo-treino-new.component.html',
  styleUrls: ['./tipo-treino-new.component.css']
})
export class TipoTreinoNewComponent implements OnInit {
  @ViewChild("form")
  form: NgForm

  tipoTreino = new TipoTreino('', '', '')
  shared: SharedService;
  message: {};
  classCss: {};


  constructor(
    private tipoTreinoService: TipoTreinoService,
    private route: ActivatedRoute
  ) { 
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    let id : string = this.route.snapshot.params['id'];
    if(id != undefined){
      this.findById(id);
    }
  }

  findById(id:string){
    this.tipoTreinoService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.tipoTreino = responseApi.data;
    },
      err => {
        this.showMessage({type: 'error', text: err['error']['errors'][0]});
      });
  }

  register(){
    this.message = {};
    this.tipoTreinoService.createOrUpdate(this.tipoTreino).subscribe((responseApi: ResponseApi) => {
      this.tipoTreino = new TipoTreino('', '', '');
      let tipoTreinoRet : TipoTreino = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `${tipoTreinoRet.nome} cadastrado com sucesso!`
      });
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      })
    });
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

  getFromGroupClass(isInvalid:boolean, isDirty){
    return {
      'form-group': true,
      'has-error': isInvalid && isDirty,
      'has-success': !isInvalid && isDirty
    }
  }
}
