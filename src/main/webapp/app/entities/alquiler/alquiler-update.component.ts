import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAlquiler, Alquiler } from 'app/shared/model/alquiler.model';
import { AlquilerService } from './alquiler.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente/cliente.service';
import { IJuego } from 'app/shared/model/juego.model';
import { JuegoService } from 'app/entities/juego/juego.service';

type SelectableEntity = ICliente | IJuego;

@Component({
  selector: 'jhi-alquiler-update',
  templateUrl: './alquiler-update.component.html',
})
export class AlquilerUpdateComponent implements OnInit {
  isSaving = false;
  clientes: ICliente[] = [];
  juegos: IJuego[] = [];
  fechaDp: any;
  fechaDeEntregaDp: any;

  editForm = this.fb.group({
    id: [],
    fecha: [],
    monto: [],
    rangoEdadAlcomprar: [],
    fechaDeEntrega: [],
    clienteId: [],
    juegos: [],
  });

  constructor(
    protected alquilerService: AlquilerService,
    protected clienteService: ClienteService,
    protected juegoService: JuegoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alquiler }) => {
      this.updateForm(alquiler);

      this.clienteService.query().subscribe((res: HttpResponse<ICliente[]>) => (this.clientes = res.body || []));

      this.juegoService.query().subscribe((res: HttpResponse<IJuego[]>) => (this.juegos = res.body || []));
    });
  }

  updateForm(alquiler: IAlquiler): void {
    this.editForm.patchValue({
      id: alquiler.id,
      fecha: alquiler.fecha,
      monto: alquiler.monto,
      rangoEdadAlcomprar: alquiler.rangoEdadAlcomprar,
      fechaDeEntrega: alquiler.fechaDeEntrega,
      clienteId: alquiler.clienteId,
      juegos: alquiler.juegos,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const alquiler = this.createFromForm();
    if (alquiler.id !== undefined) {
      this.subscribeToSaveResponse(this.alquilerService.update(alquiler));
    } else {
      this.subscribeToSaveResponse(this.alquilerService.create(alquiler));
    }
  }

  private createFromForm(): IAlquiler {
    return {
      ...new Alquiler(),
      id: this.editForm.get(['id'])!.value,
      fecha: this.editForm.get(['fecha'])!.value,
      monto: this.editForm.get(['monto'])!.value,
      rangoEdadAlcomprar: this.editForm.get(['rangoEdadAlcomprar'])!.value,
      fechaDeEntrega: this.editForm.get(['fechaDeEntrega'])!.value,
      clienteId: this.editForm.get(['clienteId'])!.value,
      juegos: this.editForm.get(['juegos'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlquiler>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IJuego[], option: IJuego): IJuego {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
