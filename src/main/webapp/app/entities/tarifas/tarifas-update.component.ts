import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITarifas, Tarifas } from 'app/shared/model/tarifas.model';
import { TarifasService } from './tarifas.service';
import { IJuego } from 'app/shared/model/juego.model';
import { JuegoService } from 'app/entities/juego/juego.service';

@Component({
  selector: 'jhi-tarifas-update',
  templateUrl: './tarifas-update.component.html',
})
export class TarifasUpdateComponent implements OnInit {
  isSaving = false;
  juegos: IJuego[] = [];

  editForm = this.fb.group({
    id: [],
    precio: [],
    juegoId: [],
  });

  constructor(
    protected tarifasService: TarifasService,
    protected juegoService: JuegoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tarifas }) => {
      this.updateForm(tarifas);

      this.juegoService
        .query({ filter: 'tarifas-is-null' })
        .pipe(
          map((res: HttpResponse<IJuego[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IJuego[]) => {
          if (!tarifas.juegoId) {
            this.juegos = resBody;
          } else {
            this.juegoService
              .find(tarifas.juegoId)
              .pipe(
                map((subRes: HttpResponse<IJuego>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IJuego[]) => (this.juegos = concatRes));
          }
        });
    });
  }

  updateForm(tarifas: ITarifas): void {
    this.editForm.patchValue({
      id: tarifas.id,
      precio: tarifas.precio,
      juegoId: tarifas.juegoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tarifas = this.createFromForm();
    if (tarifas.id !== undefined) {
      this.subscribeToSaveResponse(this.tarifasService.update(tarifas));
    } else {
      this.subscribeToSaveResponse(this.tarifasService.create(tarifas));
    }
  }

  private createFromForm(): ITarifas {
    return {
      ...new Tarifas(),
      id: this.editForm.get(['id'])!.value,
      precio: this.editForm.get(['precio'])!.value,
      juegoId: this.editForm.get(['juegoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITarifas>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IJuego): any {
    return item.id;
  }
}
