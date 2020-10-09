import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlquiler } from 'app/shared/model/alquiler.model';

@Component({
  selector: 'jhi-alquiler-detail',
  templateUrl: './alquiler-detail.component.html',
})
export class AlquilerDetailComponent implements OnInit {
  alquiler: IAlquiler | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alquiler }) => (this.alquiler = alquiler));
  }

  previousState(): void {
    window.history.back();
  }
}
