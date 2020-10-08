import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITarifas, Tarifas } from 'app/shared/model/tarifas.model';
import { TarifasService } from './tarifas.service';
import { TarifasComponent } from './tarifas.component';
import { TarifasDetailComponent } from './tarifas-detail.component';
import { TarifasUpdateComponent } from './tarifas-update.component';

@Injectable({ providedIn: 'root' })
export class TarifasResolve implements Resolve<ITarifas> {
  constructor(private service: TarifasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITarifas> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tarifas: HttpResponse<Tarifas>) => {
          if (tarifas.body) {
            return of(tarifas.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Tarifas());
  }
}

export const tarifasRoute: Routes = [
  {
    path: '',
    component: TarifasComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tIendaDeVideoJuegosApp.tarifas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TarifasDetailComponent,
    resolve: {
      tarifas: TarifasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.tarifas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TarifasUpdateComponent,
    resolve: {
      tarifas: TarifasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.tarifas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TarifasUpdateComponent,
    resolve: {
      tarifas: TarifasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.tarifas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
