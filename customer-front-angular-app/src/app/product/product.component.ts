import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit{
  products:any;
  ngOnInit(): void {
    this.http.get("http://localhost:4444/products")
      .subscribe(value => this.products=value,error => console.log(error))
  }
  constructor(private http:HttpClient) {

  }

}
