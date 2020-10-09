import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlquiler } from 'app/shared/model/alquiler.model';
import { AlquilerService } from './alquiler.service';

@Component({
  templateUrl: './alquiler-delete-dialog.component.html',
})
export class AlquilerDeleteDialogComponent {
  alquiler?: IAlquiler;

  constructor(protected alquilerService: AlquilerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.alquilerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('alquilerListModification');
      this.activeModal.close();
    });
  }
}
