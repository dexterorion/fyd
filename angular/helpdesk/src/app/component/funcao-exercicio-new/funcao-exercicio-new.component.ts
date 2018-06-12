import { FuncaoExercicio } from './../../model/funcao-exercicio.model';
import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { SharedService } from './../../service/shared.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FuncaoExercicioService } from '../../service/funcao-exercicio.service';

@Component({
  selector: 'app-funcao-exercicio-new',
  templateUrl: './funcao-exercicio-new.component.html',
  styleUrls: ['./funcao-exercicio-new.component.css']
})
export class FuncaoExercicioNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm

  funcaoExercicio = new FuncaoExercicio('', '')
  shared: SharedService;
  message: {};
  classCss: {};


  constructor(
    private funcaoExercicioService: FuncaoExercicioService,
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
    this.funcaoExercicioService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.funcaoExercicio = responseApi.data;
    },
      err => {
        this.showMessage({type: 'error', text: err['error']['errors'][0]});
      });
  }

  register(){
    this.message = {};
    this.funcaoExercicioService.createOrUpdate(this.funcaoExercicio).subscribe((responseApi: ResponseApi) => {
      this.funcaoExercicio = new FuncaoExercicio('', '');
      let funcaoExercicioRet : FuncaoExercicio = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `${funcaoExercicioRet.nome} cadastrado com sucesso!`
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