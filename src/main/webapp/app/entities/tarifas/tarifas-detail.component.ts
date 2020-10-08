import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITarifas } from 'app/shared/model/tarifas.model';

@Component({
  selector: 'jhi-tarifas-detail',
  templateUrl: './tarifas-detail.component.html',
})
export class TarifasDetailComponent implements OnInit {
  tarifas: ITarifas | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tarifas }) => (this.tarifas = tarifas));
  }

  previousState(): void {
    window.history.back();
  }
}
