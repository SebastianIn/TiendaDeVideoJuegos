import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TIendaDeVideoJuegosSharedModule } from 'app/shared/shared.module';
import { TarifasComponent } from './tarifas.component';
import { TarifasDetailComponent } from './tarifas-detail.component';
import { TarifasUpdateComponent } from './tarifas-update.component';
import { TarifasDeleteDialogComponent } from './tarifas-delete-dialog.component';
import { tarifasRoute } from './tarifas.route';

@NgModule({
  imports: [TIendaDeVideoJuegosSharedModule, RouterModule.forChild(tarifasRoute)],
  declarations: [TarifasComponent, TarifasDetailComponent, TarifasUpdateComponent, TarifasDeleteDialogComponent],
  entryComponents: [TarifasDeleteDialogComponent],
})
export class TIendaDeVideoJuegosTarifasModule {}
