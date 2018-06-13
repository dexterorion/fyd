import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { NivelExecucaoService } from './../../service/nivel-execucao.service';
import { SharedService } from './../../service/shared.service';
import { NivelExecucao } from './../../model/nivel-execucao.model';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-nivel-execucao-new',
  templateUrl: './nivel-execucao-new.component.html',
  styleUrls: ['./nivel-execucao-new.component.css']
})
export class NivelExecucaoNewComponent implements OnInit {
  @ViewChild("form")
  form: NgForm

  nivelExecucao = new NivelExecucao('', '')
  shared: SharedService;
  message: {};
  classCss: {};


  constructor(
    private nivelExecucaoService: NivelExecucaoService,
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
    this.nivelExecucaoService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.nivelExecucao = responseApi.data;
    },
      err => {
        this.showMessage({type: 'error', text: err['error']['errors'][0]});
      });
  }

  register(){
    this.message = {};
    this.nivelExecucaoService.createOrUpdate(this.nivelExecucao).subscribe((responseApi: ResponseApi) => {
      this.nivelExecucao = new NivelExecucao('', '');
      let nivelExecucaoRet : NivelExecucao = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `${nivelExecucaoRet.nome} cadastrado com sucesso!`
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
