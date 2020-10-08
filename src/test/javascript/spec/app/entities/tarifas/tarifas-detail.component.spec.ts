import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TIendaDeVideoJuegosTestModule } from '../../../test.module';
import { TarifasDetailComponent } from 'app/entities/tarifas/tarifas-detail.component';
import { Tarifas } from 'app/shared/model/tarifas.model';

describe('Component Tests', () => {
  describe('Tarifas Management Detail Component', () => {
    let comp: TarifasDetailComponent;
    let fixture: ComponentFixture<TarifasDetailComponent>;
    const route = ({ data: of({ tarifas: new Tarifas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TIendaDeVideoJuegosTestModule],
        declarations: [TarifasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TarifasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TarifasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tarifas on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tarifas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
