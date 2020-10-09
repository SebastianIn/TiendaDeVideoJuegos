import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TIendaDeVideoJuegosTestModule } from '../../../test.module';
import { AlquilerUpdateComponent } from 'app/entities/alquiler/alquiler-update.component';
import { AlquilerService } from 'app/entities/alquiler/alquiler.service';
import { Alquiler } from 'app/shared/model/alquiler.model';

describe('Component Tests', () => {
  describe('Alquiler Management Update Component', () => {
    let comp: AlquilerUpdateComponent;
    let fixture: ComponentFixture<AlquilerUpdateComponent>;
    let service: AlquilerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TIendaDeVideoJuegosTestModule],
        declarations: [AlquilerUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AlquilerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlquilerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlquilerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Alquiler(123);
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
        const entity = new Alquiler();
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
