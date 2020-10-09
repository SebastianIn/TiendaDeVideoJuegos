import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AlquilerService } from 'app/entities/alquiler/alquiler.service';
import { IAlquiler, Alquiler } from 'app/shared/model/alquiler.model';

describe('Service Tests', () => {
  describe('Alquiler Service', () => {
    let injector: TestBed;
    let service: AlquilerService;
    let httpMock: HttpTestingController;
    let elemDefault: IAlquiler;
    let expectedResult: IAlquiler | IAlquiler[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AlquilerService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Alquiler(0, currentDate, 0, 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fecha: currentDate.format(DATE_FORMAT),
            fechaDeEntrega: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Alquiler', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fecha: currentDate.format(DATE_FORMAT),
            fechaDeEntrega: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate,
            fechaDeEntrega: currentDate,
          },
          returnedFromService
        );

        service.create(new Alquiler()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Alquiler', () => {
        const returnedFromService = Object.assign(
          {
            fecha: currentDate.format(DATE_FORMAT),
            monto: 1,
            rangoEdadAlcomprar: 1,
            fechaDeEntrega: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate,
            fechaDeEntrega: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Alquiler', () => {
        const returnedFromService = Object.assign(
          {
            fecha: currentDate.format(DATE_FORMAT),
            monto: 1,
            rangoEdadAlcomprar: 1,
            fechaDeEntrega: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate,
            fechaDeEntrega: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Alquiler', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
