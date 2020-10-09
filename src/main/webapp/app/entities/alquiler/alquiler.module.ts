import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TIendaDeVideoJuegosSharedModule } from 'app/shared/shared.module';
import { AlquilerComponent } from './alquiler.component';
import { AlquilerDetailComponent } from './alquiler-detail.component';
import { AlquilerUpdateComponent } from './alquiler-update.component';
import { AlquilerDeleteDialogComponent } from './alquiler-delete-dialog.component';
import { alquilerRoute } from './alquiler.route';

@NgModule({
  imports: [TIendaDeVideoJuegosSharedModule, RouterModule.forChild(alquilerRoute)],
  declarations: [AlquilerComponent, AlquilerDetailComponent, AlquilerUpdateComponent, AlquilerDeleteDialogComponent],
  entryComponents: [AlquilerDeleteDialogComponent],
})
export class TIendaDeVideoJuegosAlquilerModule {}
