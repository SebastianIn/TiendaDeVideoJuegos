import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAlquiler, Alquiler } from 'app/shared/model/alquiler.model';
import { AlquilerService } from './alquiler.service';
import { AlquilerComponent } from './alquiler.component';
import { AlquilerDetailComponent } from './alquiler-detail.component';
import { AlquilerUpdateComponent } from './alquiler-update.component';

@Injectable({ providedIn: 'root' })
export class AlquilerResolve implements Resolve<IAlquiler> {
  constructor(private service: AlquilerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlquiler> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((alquiler: HttpResponse<Alquiler>) => {
          if (alquiler.body) {
            return of(alquiler.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Alquiler());
  }
}

export const alquilerRoute: Routes = [
  {
    path: '',
    component: AlquilerComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tIendaDeVideoJuegosApp.alquiler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AlquilerDetailComponent,
    resolve: {
      alquiler: AlquilerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.alquiler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AlquilerUpdateComponent,
    resolve: {
      alquiler: AlquilerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.alquiler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AlquilerUpdateComponent,
    resolve: {
      alquiler: AlquilerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.alquiler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
