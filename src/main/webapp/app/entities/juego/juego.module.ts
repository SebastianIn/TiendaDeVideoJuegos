import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TIendaDeVideoJuegosSharedModule } from 'app/shared/shared.module';
import { JuegoComponent } from './juego.component';
import { JuegoDetailComponent } from './juego-detail.component';
import { JuegoUpdateComponent } from './juego-update.component';
import { JuegoDeleteDialogComponent } from './juego-delete-dialog.component';
import { juegoRoute } from './juego.route';

@NgModule({
  imports: [TIendaDeVideoJuegosSharedModule, RouterModule.forChild(juegoRoute)],
  declarations: [JuegoComponent, JuegoDetailComponent, JuegoUpdateComponent, JuegoDeleteDialogComponent],
  entryComponents: [JuegoDeleteDialogComponent],
})
export class TIendaDeVideoJuegosJuegoModule {}
