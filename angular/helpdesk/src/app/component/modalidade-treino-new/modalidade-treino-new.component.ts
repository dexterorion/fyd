import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { ModalidadeTreinoService } from './../../service/modalidade-treino.service';
import { SharedService } from './../../service/shared.service';
import { ModalidadeTreino } from './../../model/modalidade-treino.model';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-modalidade-treino-new',
  templateUrl: './modalidade-treino-new.component.html',
  styleUrls: ['./modalidade-treino-new.component.css']
})
export class ModalidadeTreinoNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm

  modalidadeTreino = new ModalidadeTreino('', '', '')
  shared: SharedService;
  message: {};
  classCss: {};


  constructor(
    private modalidadeTreinoService: ModalidadeTreinoService,
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
    this.modalidadeTreinoService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.modalidadeTreino = responseApi.data;
    },
      err => {
        this.showMessage({type: 'error', text: err['error']['errors'][0]});
      });
  }

  register(){
    this.message = {};
    this.modalidadeTreinoService.createOrUpdate(this.modalidadeTreino).subscribe((responseApi: ResponseApi) => {
      this.modalidadeTreino = new ModalidadeTreino('', '', '');
      let modalidadeTreinoRet : ModalidadeTreino = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `${modalidadeTreinoRet.nome} cadastrada com sucesso!`
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
