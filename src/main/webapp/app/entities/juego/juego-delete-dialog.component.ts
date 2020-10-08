import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJuego } from 'app/shared/model/juego.model';
import { JuegoService } from './juego.service';

@Component({
  templateUrl: './juego-delete-dialog.component.html',
})
export class JuegoDeleteDialogComponent {
  juego?: IJuego;

  constructor(protected juegoService: JuegoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.juegoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('juegoListModification');
      this.activeModal.close();
    });
  }
}
