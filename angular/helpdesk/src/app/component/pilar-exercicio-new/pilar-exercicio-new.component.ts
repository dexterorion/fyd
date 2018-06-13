import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { PilarExercicioService } from './../../service/pilar-exercicio.service';
import { SharedService } from './../../service/shared.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { PilarExercicio } from '../../model/pilar-exercicio.model';

@Component({
  selector: 'app-pilar-exercicio-new',
  templateUrl: './pilar-exercicio-new.component.html',
  styleUrls: ['./pilar-exercicio-new.component.css']
})
export class PilarExercicioNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm

  pilarExercicio = new PilarExercicio('', '')
  shared: SharedService;
  message: {};
  classCss: {};


  constructor(
    private pilarExercicioService: PilarExercicioService,
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
    this.pilarExercicioService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.pilarExercicio = responseApi.data;
    },
      err => {
        this.showMessage({type: 'error', text: err['error']['errors'][0]});
      });
  }

  register(){
    this.message = {};
    this.pilarExercicioService.createOrUpdate(this.pilarExercicio).subscribe((responseApi: ResponseApi) => {
      this.pilarExercicio = new PilarExercicio('', '');
      let pilarExercicioRet : PilarExercicio = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `${pilarExercicioRet.nome} cadastrado com sucesso!`
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
