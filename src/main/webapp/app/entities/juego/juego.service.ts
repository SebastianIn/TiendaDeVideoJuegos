import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJuego } from 'app/shared/model/juego.model';

type EntityResponseType = HttpResponse<IJuego>;
type EntityArrayResponseType = HttpResponse<IJuego[]>;

@Injectable({ providedIn: 'root' })
export class JuegoService {
  public resourceUrl = SERVER_API_URL + 'api/juegos';

  constructor(protected http: HttpClient) {}

  create(juego: IJuego): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(juego);
    return this.http
      .post<IJuego>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(juego: IJuego): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(juego);
    return this.http
      .put<IJuego>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IJuego>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IJuego[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(juego: IJuego): IJuego {
    const copy: IJuego = Object.assign({}, juego, {
      fechaPublicacion: juego.fechaPublicacion && juego.fechaPublicacion.isValid() ? juego.fechaPublicacion.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaPublicacion = res.body.fechaPublicacion ? moment(res.body.fechaPublicacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((juego: IJuego) => {
        juego.fechaPublicacion = juego.fechaPublicacion ? moment(juego.fechaPublicacion) : undefined;
      });
    }
    return res;
  }
}
