import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../service/shared.service';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public shared: SharedService;
  
  constructor() { 
    this.shared = SharedService.getInstance();
    if(this.shared.user == null)
      this.shared.user = new User('', '', '', '');
  }

  ngOnInit() {
  }

  signOut() : void {
    localStorage.removeItem('currentToken');
    localStorage.removeItem('currentUser');
    this.shared.showTemplate.emit(false);
    SharedService.emptyInstance();

    window.location.href = "/login";
    window.location.reload();
  }


}
