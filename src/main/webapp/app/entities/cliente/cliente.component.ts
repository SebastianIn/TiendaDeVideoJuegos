import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICliente } from 'app/shared/model/cliente.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ClienteService } from './cliente.service';
import { ClienteDeleteDialogComponent } from './cliente-delete-dialog.component';

@Component({
  selector: 'jhi-cliente',
  templateUrl: './cliente.component.html',
})
export class ClienteComponent implements OnInit, OnDestroy {
  cliente!: ICliente;
  mostrar!: boolean;

  cedula!: string;

  constructor(
    protected clienteService: ClienteService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.mostrar = false;
  }

  ngOnDestroy(): void {}

  busquedaCliente(): void {
    if (this.cedula) {
      this.clienteService.findByCedula(this.cedula).subscribe(
        (res: HttpResponse<ICliente>) => this.onSuccess(res.body),
        () => this.onError()
      );
    }
  }

  previousState() {
    this.mostrar = false;
  }

  protected onSuccess(data: ICliente | null): void {
    if (data) {
      this.cliente = data;
      this.mostrar = true;
    }
  }

  protected onError(): void {
    this.mostrar = false;
  }
}
