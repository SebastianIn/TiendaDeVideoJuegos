import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TIendaDeVideoJuegosTestModule } from '../../../test.module';
import { TarifasUpdateComponent } from 'app/entities/tarifas/tarifas-update.component';
import { TarifasService } from 'app/entities/tarifas/tarifas.service';
import { Tarifas } from 'app/shared/model/tarifas.model';

describe('Component Tests', () => {
  describe('Tarifas Management Update Component', () => {
    let comp: TarifasUpdateComponent;
    let fixture: ComponentFixture<TarifasUpdateComponent>;
    let service: TarifasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TIendaDeVideoJuegosTestModule],
        declarations: [TarifasUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TarifasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TarifasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TarifasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tarifas(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tarifas();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
