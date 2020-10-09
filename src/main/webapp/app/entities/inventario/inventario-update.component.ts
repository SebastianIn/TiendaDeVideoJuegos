import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInventario, Inventario } from 'app/shared/model/inventario.model';
import { InventarioService } from './inventario.service';
import { IJuego } from 'app/shared/model/juego.model';
import { JuegoService } from 'app/entities/juego/juego.service';

@Component({
  selector: 'jhi-inventario-update',
  templateUrl: './inventario-update.component.html',
})
export class InventarioUpdateComponent implements OnInit {
  isSaving = false;
  juegos: IJuego[] = [];

  editForm = this.fb.group({
    id: [],
    total: [],
    disponibles: [],
    enAlquiler: [],
    juegoId: [],
  });

  constructor(
    protected inventarioService: InventarioService,
    protected juegoService: JuegoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inventario }) => {
      this.updateForm(inventario);

      this.juegoService
        .query({ filter: 'inventario-is-null' })
        .pipe(
          map((res: HttpResponse<IJuego[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IJuego[]) => {
          if (!inventario.juegoId) {
            this.juegos = resBody;
          } else {
            this.juegoService
              .find(inventario.juegoId)
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

  updateForm(inventario: IInventario): void {
    this.editForm.patchValue({
      id: inventario.id,
      total: inventario.total,
      disponibles: inventario.disponibles,
      enAlquiler: inventario.enAlquiler,
      juegoId: inventario.juegoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inventario = this.createFromForm();
    if (inventario.id !== undefined) {
      this.subscribeToSaveResponse(this.inventarioService.update(inventario));
    } else {
      this.subscribeToSaveResponse(this.inventarioService.create(inventario));
    }
  }

  private createFromForm(): IInventario {
    return {
      ...new Inventario(),
      id: this.editForm.get(['id'])!.value,
      total: this.editForm.get(['total'])!.value,
      disponibles: this.editForm.get(['disponibles'])!.value,
      enAlquiler: this.editForm.get(['enAlquiler'])!.value,
      juegoId: this.editForm.get(['juegoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInventario>>): void {
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
