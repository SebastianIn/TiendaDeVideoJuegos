import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IJuego, Juego } from 'app/shared/model/juego.model';
import { JuegoService } from './juego.service';

@Component({
  selector: 'jhi-juego-update',
  templateUrl: './juego-update.component.html',
})
export class JuegoUpdateComponent implements OnInit {
  isSaving = false;
  fechaPublicacionDp: any;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    fechaPublicacion: [],
    protagonistas: [],
    director: [],
    productor: [],
    tecnologia: [],
  });

  constructor(protected juegoService: JuegoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ juego }) => {
      this.updateForm(juego);
    });
  }

  updateForm(juego: IJuego): void {
    this.editForm.patchValue({
      id: juego.id,
      nombre: juego.nombre,
      fechaPublicacion: juego.fechaPublicacion,
      protagonistas: juego.protagonistas,
      director: juego.director,
      productor: juego.productor,
      tecnologia: juego.tecnologia,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const juego = this.createFromForm();
    if (juego.id !== undefined) {
      this.subscribeToSaveResponse(this.juegoService.update(juego));
    } else {
      this.subscribeToSaveResponse(this.juegoService.create(juego));
    }
  }

  private createFromForm(): IJuego {
    return {
      ...new Juego(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      fechaPublicacion: this.editForm.get(['fechaPublicacion'])!.value,
      protagonistas: this.editForm.get(['protagonistas'])!.value,
      director: this.editForm.get(['director'])!.value,
      productor: this.editForm.get(['productor'])!.value,
      tecnologia: this.editForm.get(['tecnologia'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJuego>>): void {
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
}
