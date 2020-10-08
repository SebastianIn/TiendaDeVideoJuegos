import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IJuego, Juego } from 'app/shared/model/juego.model';
import { JuegoService } from './juego.service';
import { JuegoComponent } from './juego.component';
import { JuegoDetailComponent } from './juego-detail.component';
import { JuegoUpdateComponent } from './juego-update.component';

@Injectable({ providedIn: 'root' })
export class JuegoResolve implements Resolve<IJuego> {
  constructor(private service: JuegoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJuego> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((juego: HttpResponse<Juego>) => {
          if (juego.body) {
            return of(juego.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Juego());
  }
}

export const juegoRoute: Routes = [
  {
    path: '',
    component: JuegoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tIendaDeVideoJuegosApp.juego.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JuegoDetailComponent,
    resolve: {
      juego: JuegoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.juego.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: JuegoUpdateComponent,
    resolve: {
      juego: JuegoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.juego.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: JuegoUpdateComponent,
    resolve: {
      juego: JuegoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tIendaDeVideoJuegosApp.juego.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
