import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.TIendaDeVideoJuegosClienteModule),
      },
      {
        path: 'juego',
        loadChildren: () => import('./juego/juego.module').then(m => m.TIendaDeVideoJuegosJuegoModule),
      },
      {
        path: 'tarifas',
        loadChildren: () => import('./tarifas/tarifas.module').then(m => m.TIendaDeVideoJuegosTarifasModule),
      },
      {
        path: 'alquiler',
        loadChildren: () => import('./alquiler/alquiler.module').then(m => m.TIendaDeVideoJuegosAlquilerModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TIendaDeVideoJuegosEntityModule {}
