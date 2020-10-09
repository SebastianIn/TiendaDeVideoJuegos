import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TIendaDeVideoJuegosTestModule } from '../../../test.module';
import { AlquilerDetailComponent } from 'app/entities/alquiler/alquiler-detail.component';
import { Alquiler } from 'app/shared/model/alquiler.model';

describe('Component Tests', () => {
  describe('Alquiler Management Detail Component', () => {
    let comp: AlquilerDetailComponent;
    let fixture: ComponentFixture<AlquilerDetailComponent>;
    const route = ({ data: of({ alquiler: new Alquiler(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TIendaDeVideoJuegosTestModule],
        declarations: [AlquilerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AlquilerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlquilerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load alquiler on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alquiler).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
