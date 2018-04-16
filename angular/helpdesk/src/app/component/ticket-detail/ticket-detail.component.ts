import { ResponseApi } from './../../model/response-api';
import { ActivatedRoute } from '@angular/router';
import { TicketService } from './../../service/ticket.service';
import { Ticket } from './../../model/ticket.model';
import { SharedService } from './../../service/shared.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  ticket = new Ticket('',0,'','','','',null,null,'',null)
  shared: SharedService;
  message: {};
  classCss: {};

  constructor(
    private ticketService : TicketService,
    private route : ActivatedRoute
  ) {
      this.shared = SharedService.getInstance();
   }

  ngOnInit() {
    let id : string = this.route.snapshot.params['id'];
    if ( id != undefined){
      this.findById(id);
    }
  }

  findById(id:string){
    this.ticketService.findById(id).subscribe((responseApi: ResponseApi) => {
      console.log(responseApi.data);
      this.ticket = responseApi.data;
      this.ticket.date = new Date(this.ticket.date).toISOString();
    },
      err => {
        this.showMessage({type: 'error', text: err['error']['errors'][0]});
      });
  }

  changeStatus(status:string) : void {
    this.ticketService.changeStatus(status,this.ticket).subscribe((responseApi:ResponseApi) =>{
      this.ticket = responseApi.data;
      this.ticket.date = new Date(this.ticket.date).toISOString();
      this.showMessage({
        type: 'success',
        text: 'Successfully changed status'
      });
    }, err => {
      this.showMessage({type: 'error', text: err['error']['errors'][0]});
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
