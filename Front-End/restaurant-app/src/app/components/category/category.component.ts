import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import {Category} from "../../../models/category";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService ,private activatedRoute: ActivatedRoute) {
  }




  ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
          this.getCategories()
        })
    }

  getCategories() {
    this.categoryService.getAllCategories().subscribe(
      value => {
        this.categories = value ;
      }
    )
  }

}
