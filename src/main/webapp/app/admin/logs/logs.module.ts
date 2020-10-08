import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TIendaDeVideoJuegosSharedModule } from 'app/shared/shared.module';

import { LogsComponent } from './logs.component';

import { logsRoute } from './logs.route';

@NgModule({
  imports: [TIendaDeVideoJuegosSharedModule, RouterModule.forChild([logsRoute])],
  declarations: [LogsComponent],
})
export class LogsModule {}
