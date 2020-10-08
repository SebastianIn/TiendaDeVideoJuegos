import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { JuegoService } from 'app/entities/juego/juego.service';
import { IJuego, Juego } from 'app/shared/model/juego.model';

describe('Service Tests', () => {
  describe('Juego Service', () => {
    let injector: TestBed;
    let service: JuegoService;
    let httpMock: HttpTestingController;
    let elemDefault: IJuego;
    let expectedResult: IJuego | IJuego[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(JuegoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Juego(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaPublicacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Juego', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaPublicacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaPublicacion: currentDate,
          },
          returnedFromService
        );

        service.create(new Juego()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Juego', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            fechaPublicacion: currentDate.format(DATE_FORMAT),
            protagonistas: 'BBBBBB',
            director: 'BBBBBB',
            productor: 'BBBBBB',
            tecnologia: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaPublicacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Juego', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            fechaPublicacion: currentDate.format(DATE_FORMAT),
            protagonistas: 'BBBBBB',
            director: 'BBBBBB',
            productor: 'BBBBBB',
            tecnologia: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaPublicacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Juego', () => {
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
