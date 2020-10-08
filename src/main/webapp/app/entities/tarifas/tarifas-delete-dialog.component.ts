import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITarifas } from 'app/shared/model/tarifas.model';
import { TarifasService } from './tarifas.service';

@Component({
  templateUrl: './tarifas-delete-dialog.component.html',
})
export class TarifasDeleteDialogComponent {
  tarifas?: ITarifas;

  constructor(protected tarifasService: TarifasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tarifasService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tarifasListModification');
      this.activeModal.close();
    });
  }
}
